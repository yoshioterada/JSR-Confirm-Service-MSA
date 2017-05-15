/*
 * Copyright 2017 Yoshio Terada
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yoshio3.services;

import com.yoshio3.persistence.JsrList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * Refer to the DB
 *
 * @author Yoshio Terada
 */
@Path("/jsr")
public class JSRConfirmService {

    @Context
    protected HttpServletResponse response;

    @PersistenceContext(unitName = "DATABASE_PU")
    EntityManager entityManager;

    @GET
    @Path("{id}")
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public JsrList getJSRFromID(@PathParam("id") Long id) {
        try {
            TypedQuery<JsrList> query = entityManager
                    .createNamedQuery("JsrList.findByJsrId", JsrList.class);
            query.setParameter("jsrId", id);
            return query.getSingleResult();

        } catch (NoResultException nre) {
            Logger.getLogger(JSRConfirmService.class.getName()).log(Level.SEVERE, null, nre);
            throw new WebApplicationException("Can't find JSR", 404);
        }
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/search")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED + "; charset=UTF-8")
    public List<JsrList> getJSRFromName(@QueryParam("name") String name) {
        TypedQuery<JsrList> query = entityManager
                .createNamedQuery("JsrList.findByLIKENameOfJsr", JsrList.class);

        query.setParameter("nameOfJsr", "%" + name + "%");
        List<JsrList> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return query.getResultList();
        } else {
            throw new WebApplicationException("Can't find JSR", 404);
        }
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/actives")
    @Consumes("application/json; charset=UTF-8")
    public List<JsrList> getActiveJSR() {
        TypedQuery<JsrList> query = entityManager
                .createNamedQuery("JsrList.findByCurrentStatus", JsrList.class);
        query.setParameter("currentStatus", "Active");
        List<JsrList> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return query.getResultList();
        } else {
            throw new WebApplicationException("Can't find JSR", 404);
        }
    }
    
    
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/stages")
    @Consumes("application/json; charset=UTF-8")
    public String getStages() {
        TypedQuery<String> query = entityManager
                .createNamedQuery("JsrList.DistinctLatestStage", String.class);
        List<String> resultList = query.getResultList();
        JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
        resultList.stream().forEach((String stages) -> createArrayBuilder.add(stages));
        JsonObject jsonObj = Json.createObjectBuilder().add("stagesname", createArrayBuilder.build()).build();
        return jsonObj.toString();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/statuses")
    @Consumes("application/json; charset=UTF-8")
    public String getStatuses() {
        TypedQuery<String> query = entityManager
                .createNamedQuery("JsrList.DistinctStatus", String.class);
        List<String> resultList = query.getResultList();
        
        JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
        resultList.stream().forEach((String status) -> createArrayBuilder.add(status));
        JsonObject jsonObj = Json.createObjectBuilder().add("statusesname", createArrayBuilder.build()).build();
        return jsonObj.toString();
    }
}

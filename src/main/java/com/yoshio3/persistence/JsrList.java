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
package com.yoshio3.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * O/R Mapping Class for JSR_LIST TABLE
 *
 * @author Yoshio Terada
 */
@Entity
@Table(name = "JSR_LIST")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "JsrList.findAll", query = "SELECT j FROM JsrList j"),
    @NamedQuery(name = "JsrList.findById", query = "SELECT j FROM JsrList j WHERE j.id = :id"),
    @NamedQuery(name = "JsrList.findByDescription", query = "SELECT j FROM JsrList j WHERE j.description = :description"),
    @NamedQuery(name = "JsrList.findByEffectiveDate", query = "SELECT j FROM JsrList j WHERE j.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "JsrList.findByEndDate", query = "SELECT j FROM JsrList j WHERE j.endDate = :endDate"),
    @NamedQuery(name = "JsrList.findByJsrId", query = "SELECT j FROM JsrList j WHERE j.jsrId = :jsrId"),
    @NamedQuery(name = "JsrList.findByLatestStage", query = "SELECT j FROM JsrList j WHERE j.latestStage = :latestStage"),
    @NamedQuery(name = "JsrList.findByNameOfJsr", query = "SELECT j FROM JsrList j WHERE j.nameOfJsr = :nameOfJsr"),
    @NamedQuery(name = "JsrList.findByLIKENameOfJsr", query = "SELECT j FROM JsrList j WHERE j.nameOfJsr LIKE :nameOfJsr"),
    @NamedQuery(name = "JsrList.DistinctLatestStage", query = "SELECT DISTINCT j.latestStage FROM JsrList j ORDER BY j.latestStage"),    
    @NamedQuery(name = "JsrList.findByReason", query = "SELECT j FROM JsrList j WHERE j.reason = :reason"),
    @NamedQuery(name = "JsrList.findByStartDate", query = "SELECT j FROM JsrList j WHERE j.startDate = :startDate"),
    @NamedQuery(name = "JsrList.DistinctStatus", query = "SELECT DISTINCT j.currentStatus FROM JsrList j ORDER BY j.currentStatus"),    
    @NamedQuery(name = "JsrList.findByCurrentStatus", query = "SELECT j FROM JsrList j WHERE j.currentStatus = :currentStatus")})

public class JsrList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Size(max = 2048)
    @Column(length = 2048)
    private String description;
    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "JSR_ID")
    private BigInteger jsrId;
    @Size(max = 255)
    @Column(name = "LATEST_STAGE", length = 255)
    private String latestStage;
    @Size(max = 255)
    @Column(name = "NAME_OF_JSR", length = 255)
    private String nameOfJsr;
    @Size(max = 2048)
    @Column(length = 2048)
    private String reason;
    @Lob
    @Column(name = "SPEC_LEADS")
    private List<String> specLeads;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Size(max = 255)
    @Column(name = "CURRENT_STATUS", length = 255)
    private String currentStatus;

    public JsrList() {
    }

    public JsrList(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigInteger getJsrId() {
        return jsrId;
    }

    public void setJsrId(BigInteger jsrId) {
        this.jsrId = jsrId;
    }

    public String getLatestStage() {
        return latestStage;
    }

    public void setLatestStage(String latestStage) {
        this.latestStage = latestStage;
    }

    public String getNameOfJsr() {
        return nameOfJsr;
    }

    public void setNameOfJsr(String nameOfJsr) {
        this.nameOfJsr = nameOfJsr;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getSpecLeads() {
        return specLeads;
    }

    public void setSpecLeads(List<String> specLeads) {
        this.specLeads = specLeads;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsrList)) {
            return false;
        }
        JsrList other = (JsrList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JsrList{" + "id=" + id + ", description=" + description + ", effectiveDate=" + effectiveDate + ", endDate=" + endDate + ", jsrId=" + jsrId + ", latestStage=" + latestStage + ", nameOfJsr=" + nameOfJsr + ", reason=" + reason + ", specLeads=" + specLeads + ", startDate=" + startDate + ", currentStatus=" + currentStatus + '}';
    }
}

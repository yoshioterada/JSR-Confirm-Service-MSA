package com.yoshio3.persistence;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T07:30:28")
@StaticMetamodel(JsrList.class)
public class JsrList_ { 

    public static volatile SingularAttribute<JsrList, String> reason;
    public static volatile SingularAttribute<JsrList, String> nameOfJsr;
    public static volatile SingularAttribute<JsrList, Date> endDate;
    public static volatile SingularAttribute<JsrList, String> currentStatus;
    public static volatile SingularAttribute<JsrList, String> description;
    public static volatile SingularAttribute<JsrList, String> latestStage;
    public static volatile SingularAttribute<JsrList, List> specLeads;
    public static volatile SingularAttribute<JsrList, Long> id;
    public static volatile SingularAttribute<JsrList, Date> effectiveDate;
    public static volatile SingularAttribute<JsrList, Date> startDate;
    public static volatile SingularAttribute<JsrList, BigInteger> jsrId;

}
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
<jsp:attribute name="header">
         Success
    </jsp:attribute>
    <jsp:body>

        <div class="box align-center">

            <br>
            <t1>Tak for din ordre!</t1>
            <br>
            <br>
            <t1>Din ordre er nu sendt til behandling.</t1>
            <br>
            <br>
            <t1>Admin vil nu behandle din ordre. Der vil være lidt behandlingstid.</t1>
            <br>

            <br>
            <a href="${pageContext.request.contextPath}/index">Tilbage til forsiden og se mere på ordren. </a>
            </br>

        </div>


    </jsp:body>
</t:pagetemplate>

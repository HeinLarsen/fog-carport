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

        <div class="box">
            <br>
            <t2>Tak for din ordre!</t2>
            <br>
            <br>
            <t2>Din ordre er nu sendt til behandling.</t2>
            <br>
            <br>
            <t2>Admin vil nu behandle din ordre. Der vil være lidt behandlingstid.</t2>
            <br>

            <br>
            <a href="index.jsp">Tilbage til forsiden og se mere på ordren. </a>
            </br>

        </div>


    </jsp:body>
</t:pagetemplate>

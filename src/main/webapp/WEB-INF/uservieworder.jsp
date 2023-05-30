<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Brugeren kan se ordre detalje her:
    </jsp:attribute>

    <jsp:body>

        <div class="mt-5 align-center griddy">
            <div class="box">
                <h2>Bruger info:</h2>
                <div class="box">
                    <br>
                    Navn: ${requestScope.user.firstName} ${requestScope.user.lastName}
                    <br>
                    Email: ${requestScope.user.email}
                    <br>
                    Telefon nummer: ${requestScope.user.phoneNumber}
                    <br>
                    Adresse: ${requestScope.user.address}
                    <br>
                    Postnummer: ${requestScope.user.zip}
                    <br>
                    Medlemskab: ${requestScope.user.membershipId}
                    <br/>

                </div>
            </div>
        </div>
        <a href="src/main/webapp/models/buildList-1.stl" download>download ordre</a>
        <a href="src/main/webapp/models/materialList-1.stl" download>download ordre</a>

    </jsp:body>

</t:pagetemplate>
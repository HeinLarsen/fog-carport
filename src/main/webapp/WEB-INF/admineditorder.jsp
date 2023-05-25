<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
<jsp:attribute name="header">
         Order Menu
    </jsp:attribute>

    <jsp:body>

        <div class="mt-5 align-center griddy">
            <div class="box">
                <h2>Stykliste:</h2>

                    ${requestScope.order.getOrderItems()}

            </div>


            <div class="box">
                <h2>Bruger info:</h2>
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

            <div class="box">
                <h2>Kontrolpanel:</h2>
                <button type="submit">Godkend</button>
                <button type="submit">Annuller</button>
                <button type="submit">Slet ordre</button>

            </div>

            <div class="box">
                <h2>Pris: </h2>

            </div>

            <div class="box">
                <h2>Filer: </h2>
            </div>
        </div>


    </jsp:body>
</t:pagetemplate>

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
        <c:forEach items="${requestScope.users}" var="user">
            <div class="box">
                <br>
                <t2>Bruger info:</t2>
                <br>
                Navn: ${user.firstName} ${user.lastName}
                <br>
                Email: ${user.email}
                <br>
                Telefon nummer: ${user.phoneNumber}
                <br>
                Adresse: ${user.address}
                <br>
                Postnummer: ${user.zip}
                <br>
                Medlemskab: ${user.membership}
                <br/>
            </div>
        </c:forEach>

        <c:forEach items="${requestScope.orders}" var="order">
            <t2>Stykliste:<t2/>

            <div class="box">
                    ${order.getOrderItems()}
            </div>

            <div class="box">
                    ${order.getOrderItems()}
            </div>
        </c:forEach>

        <div class="box">
        <button type="submit">Godkend</button>
        <button type="submit">Annuller</button>
        <button type="submit">Slet ordre</button>
        </div>

    </jsp:body>
</t:pagetemplate>

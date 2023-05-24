<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Bruger ordre oversigt
    </jsp:attribute>

    <jsp:body>
    <div class="griddy mt-5 align-center">
        <div class="box">
            <h2>Bruger info:</h2>
            <c:forEach items="${requestScope.userinfo}" var="user">
                <div class="box kunde">
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
        </div>

            <div class="box">
                <h2>Order:</h2>
                <c:forEach items="${requestScope.orders}" var="order">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Bestilt:</th>
                        <th>Oprettelses dato</th>
                        <th>Status</th>
                        <th>Pris</th>
                        <th>Se Ordre</th>
                    </tr>

                    <td>
                        <p>${order.orderID}</p>
                    <td>
                        <p>${order.length}</p>
                        <p>${order.width}</p>
                        <p>${order.shed}</p>
                    <td>
                        <p>${order.status}</p>
                    <td>
                        <p>${order.created}</p>
                    <td>
                        <p>${order.totalPrice}kr,-</p>
                    </td>

                    <a href="showusersorder?id=${order.orderID}" value="${order.orderID}"
                       class="btn btn-primary">Redigere</a>

                    </thead>
                </table>
            </div>
        </c:forEach>
    </div>

    </jsp:body>

</t:pagetemplate>

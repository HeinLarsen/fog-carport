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

                    <a href="showuserinfosorder?id=${order.orderID}" value="${order.orderID}"
                       class="btn btn-primary">Redigere</a>

                    </thead>
                </table>
            </div>
            </c:forEach>
        </div>
        </div>

    </jsp:body>

</t:pagetemplate>

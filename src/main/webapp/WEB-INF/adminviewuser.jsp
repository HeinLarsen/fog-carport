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
            <div class="box homepage_grid_box box_width">
                <h3>Order liste:</h3>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header">ID</th>
                            <th class="sticky-header">Order</th>
                            <th class="sticky-header">Oprettelses dato</th>
                            <th class="sticky-header">Status</th>
                            <th class="sticky-header">Kunde</th>
                        </tr>
                        <c:forEach items="${requestScope.ordersList}" var="order">
                            <tr onclick="location.href='admineditorder?id=${order.orderID}'">
                                <td>${order.orderID}</td>
                                <td>${order.length} cm x ${order.width} cm, shed: ${order.shed}</td>
                                <td>${order.created}</td>
                                <td>${order.status}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            </div>
            </c:forEach>
        </div>
        </div>

    </jsp:body>

</t:pagetemplate>

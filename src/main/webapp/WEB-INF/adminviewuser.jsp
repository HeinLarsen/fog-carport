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
            <div class="box homepage_grid_box box_width">
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
                Medlemskab:
                <c:choose>
                    <c:when test="${requestScope.user.membershipId eq 1}">
                        Basic
                    </c:when>
                    <c:when test="${requestScope.user.membershipId eq 2}">
                        Member
                    </c:when>
                    <c:when test="${requestScope.user.membershipId eq 3}">
                        Worker
                    </c:when>
                    <c:otherwise>
                        Unknown
                    </c:otherwise>
                </c:choose>
                <br/>
            </div>

            <div class="box homepage_grid_box">
                <h3>Order liste:</h3>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header">ID</th>
                            <th class="sticky-header">Order</th>
                            <th class="sticky-header">Oprettelses dato</th>
                            <th class="sticky-header">Status</th>
                        </tr>
                        <c:forEach items="${requestScope.orderList}" var="order">
                            <tr onclick="location.href='admineditorder?id=${order.orderID}&userid=${order.userID}'">
                                <td>${order.orderID}</td>
                                <td>${order.length} cm x ${order.width} cm, shed: ${order.shed}</td>
                                <td>${order.created}</td>
                                <td>${order.status}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>

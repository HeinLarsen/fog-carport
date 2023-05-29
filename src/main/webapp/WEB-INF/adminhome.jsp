<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Admin Page
    </jsp:attribute>

    <jsp:body>
        <div class="griddy">
            <div class="box homepage_grid_box box_width">
                <h3>Kunde liste:</h3>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header">ID</th>
                            <th class="sticky-header">Email</th>
                            <th class="sticky-header">Navn</th>
                        </tr>
                        <c:forEach items="${requestScope.usersList}" var="user">
                            <tr onclick="location.href='adminviewuser?id=${user.id}'">
                                <td>${user.id}</td>
                                <td>${user.email}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="box homepage_grid_box">
                <h3>Ubehandlet order liste:</h3>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header">ID</th>
                            <th class="sticky-header">Order</th>
                            <th class="sticky-header">Oprettelses dato</th>
                            <th class="sticky-header">Status</th>
                            <th class="sticky-header">Kunde</th>
                        </tr>
                        <c:forEach items="${requestScope.ordersPending}" var="ordersstatus">
                            <tr onclick="location.href='admineditorder?id=${ordersstatus.orderID}&userid=${ordersstatus.userID}'">
                                <td>${ordersstatus.orderID}</td>
                                <td>${ordersstatus.length} cm x ${ordersstatus.width} cm,
                                    shed: ${ordersstatus.shed}</td>
                                <td>${ordersstatus.created}</td>
                                <td>${ordersstatus.status}</td>
                                <td>${ordersstatus.userID}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="box homepage_grid_box box_width">
                <h3>Order liste:</h3>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header">ID</th>
                            <th class="sticky-header">Order</th>
                            <th class="sticky-header">Status</th>
                            <th class="sticky-header">Kunde</th>
                        </tr>
                        <c:forEach items="${requestScope.ordersList}" var="order">
                            <tr onclick="location.href='admineditorder?id=${order.orderID}&userid=${order.userID}'">
                                <td>${order.orderID}</td>
                                <td>${order.length} cm x ${order.width} cm, shed: ${order.shed}</td>
                                <td>${order.status}</td>
                                <td>${order.userID}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>


</t:pagetemplate>

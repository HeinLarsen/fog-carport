<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Admin Page
    </jsp:attribute>

    <jsp:body>

    <div class="temp">
        <div class="box homepage_grid_box">
        <t2>Kunde liste:</t2>
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Navn</th>
                </tr>
                <c:forEach items="${requestScope.usersList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td><a href="/showusersorder?id=${user.id}" value="${user.id}">Se brugerens ordre</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>



        <div class="box homepage_grid_box">
            <t2>Order liste:</t2>
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Order</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td>${order.orderID}</td>
                        <td>${order.length} ${order.width} ${order.shed}</td>
                        <td>${order.status}</td>
                        <td><a href="/admineditorder?id=${order.orderID}">${order.orderID} ${order.length} ${order.width} ${order.shed} ${order.status}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>


        <div class="box homepage_grid_box">
            <t2>Ubehandlet order liste:</t2>
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Order</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${requestScope.ordersPending}" var="ordersstatus">
                    <tr>
                        <td>ID</td>
                        <td>Ordre</td>
                        <td>Status</td>
                        <td><a href="/admineditorder?id=${ordersstatus.orderID}">${order.orderID} ${order.length} ${order.width} ${order.shed} ${order.status}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


    </jsp:body>

</t:pagetemplate>

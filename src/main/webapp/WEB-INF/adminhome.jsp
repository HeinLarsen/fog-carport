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

        <t2>Kunde liste:</t2>
        <div class="box kunde">
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Navn</th>
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


        <t2>Order liste:</t2>
        <div class="box order">
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
                        <td><a href="/admineditorder?id=${order.orderID}">Se ordre </a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <t2>Ubehandlet order liste:</t2>
        <div class="box ubehandletorder">
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
                        <td><a href="/admineditorder?id=${ordersstatus.orderID}">Redigerer ordre</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>


    </jsp:body>

</t:pagetemplate>

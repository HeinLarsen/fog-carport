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
            <div class="box homepage_grid_box">
                <t2>Kunde liste:</t2>
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


            <div class="box homepage_grid_box">
                <t2>Order liste:</t2>
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>ID</th>
                        <th>Order</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${requestScope.ordersList}" var="order">
                        <tr onclick="location.href='admineditorder?id=${order.orderID}'">
                            <td>${order.orderID}</td>
                            <td>${order.length} cm x ${order.width} cm, shed: ${order.shed}</td>
                            <td>${order.status}</td>
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
                        <th>Kunde</th>
                    </tr>
                    <c:forEach items="${requestScope.ordersPending}" var="ordersstatus">
                        <tr onclick="location.href='admineditorder?id=${ordersstatus.orderID}'">
                            <td>${ordersstatus.orderID}</td>
                            <td>${ordersstatus.length} cm x ${ordersstatus.width} cm, shed: ${ordersstatus.shed}</td>
                            <td>${ordersstatus.status}</td>
                            <td>${user.firstName} ${user.lastName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
    </jsp:body>

</t:pagetemplate>

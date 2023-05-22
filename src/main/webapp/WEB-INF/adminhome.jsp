<%--
  Created by IntelliJ IDEA.
  User: x-drive
  Date: 22/05/2023
  Time: 10.12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         Admin Page
    </jsp:attribute>

    <jsp:body>

        <t2>Kunde liste:</t2>
        <div class="box">
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Navn</th>
                </tr>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td><a href="showusersorder?id=${user.id}">${user.email} ${user.firstName} ${user.lastName}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>


        <t2>Order liste:</t2>
        <div class="box">
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

        <t2>Ubehandlet order liste:</t2>
        <div class="box">
            <table class="table table-striped table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Order</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td>ID</td>
                        <td>Ordre</td>
                        <td>Status</td>
                        <td><a href="/admineditorder?id=${order.orderID}">${order.orderID} ${order.length} ${order.width} ${order.shed} ${order.status}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>


    </jsp:body>

</t:pagetemplate>

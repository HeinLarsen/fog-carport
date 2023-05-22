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
        </div>


        <c:forEach items="${requestScope.order}" var="order">
            <div class="box">
            <t2>Order liste:</t2>
            <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Ordre</th>
                <th>Status</th>
            </tr>
        </c:forEach>

        <c:forEach items="${requestScope.order}" var="order">
            <div class="box">
            <t2>Ubehandlet order liste:</t2>
            <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Ordre</th>
                <th>Status</th>
            </tr>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>

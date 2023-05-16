
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Users
    </jsp:attribute>

    <jsp:body>
        <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Rolle</th>
                <th>Status</th>
                <th>Se Order</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.status}</td>
                    <td>
                        <a href="balance?id=${user.id}" value="${user.id}" class="btn btn-primary">Opdater balance</a>
                        <a href="showallorderitemsbyorderid?id=${user.id}" value="${user.id}" class="btn btn-primary">Se alle brugerens ordrer</a>
                    </td>
                </tr>
            </c:forEach>

        </table>


    </jsp:body>

</t:pagetemplate>

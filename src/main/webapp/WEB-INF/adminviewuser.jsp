<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Brugeren og Order
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${requestScope.users}" var="user">
            <div class="box">
                <br>
                <t2>Bruger info:</t2>
                <br>
                Navn: ${user.firstName} ${user.lastName}
                <br>
                Email: ${user.email}
                <br>
                Telefon nummer: ${user.phoneNumber}
                <br>
                Adresse: ${user.address}
                <br>
                Postnummer: ${user.zip}
                <br>
                Medlemskab: ${user.membership}
                <br/>
            </div>
        </c:forEach>

        <c:forEach items="${requestScope.orders}" var="order">
            <div class="box">
                <t2>Order:</t2>
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

                    <a href="showusersorder?id=${order.orderID}" value="${order.orderID}"
                       class="btn btn-primary">Redigere</a>

                    </thead>
                </table>
            </div>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>

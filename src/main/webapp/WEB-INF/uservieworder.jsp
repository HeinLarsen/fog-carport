<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Brugeren kan se ordre detalje her:
    </jsp:attribute>

    <jsp:body>

        <div class="mt-5 align-center griddy">
            <div class="box homepage_grid_box">
                <h2>Stykliste:</h2>
                <div class="scrollable-table">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th class="sticky-header" style="background-color: lightgrey">Materiale</th>
                            <th class="sticky-header" style="background-color: lightgrey">Længde</th>
                            <th class="sticky-header" style="background-color: lightgrey">Antal</th>
                            <th class="sticky-header" style="background-color: lightgrey">Enhed</th>
                            <th class="sticky-header" style="background-color: lightgrey">Beskrivelse</th>
                        </tr>
                        <tr>
                            <th class="sticky-header" style="background-color: lightgrey">Træ</th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>

                            <c:forEach items="${requestScope.orderItemWood}" var="wood">
                                <td>${wood.material.height} x ${wood.material.width}. ${wood.material.category}</td>
                                <td>${wood.material.length}</td>
                                <td>${wood.quantity}</td>
                                <td>${wood.material.unit}</td>
                                <td>${wood.description}</td>
                            </c:forEach>
                        </tr>

                        <tr>
                            <th class="sticky-header" style="background-color: lightgrey">Tagplader</th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>

                            <c:forEach items="${requestScope.orderItemRoofTile}" var="rooftile">
                            <td>${rooftile.material.name}</td>
                            <td>${rooftile.material.length}</td>
                            <td>${rooftile.quantity}</td>
                            <td>${rooftile.material.unit} </td>
                            <td>${rooftile.description}</td>
                            </c:forEach>
                        <tr>

                        <tr>
                            <th class="sticky-header" style="background-color: lightgrey">Skruer</th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>

                            <c:forEach items="${requestScope.orderItemScrew}" var="screw">
                            <td>${screw.material.name}</td>
                            <td></td>
                            <td>${screw.quantity}</td>
                            <td>${screw.material.unit}</td>
                            <td>${screw.description}</td>
                            </c:forEach>

                        <tr>

                        <tr>
                            <th class="sticky-header" style="background-color: lightgrey">Beslag</th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>
                            <th class="sticky-header" style="background-color: lightgrey"></th>

                            <c:forEach items="${requestScope.orderItemFitting}" var="fitting">
                                <td>${fitting.material.name}</td>
                                <td>${fitting.material.length} ${fitting.material.unit}</td>
                                <td>${fitting.material.price}</td>
                                <td>${fitting.quantity}</td>
                                <td>${fitting.description}</td>
                            </c:forEach>
                        </tr>

                    </table>

                </div>

            </div>


            <div class="box">
                <h2>Status på Ordren:</h2>
                <br>
                        ${requestScope.order.status}
                </br>
            </div>


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
            </div>


            <div class="box">
                <h2>Pris: </h2>
                    ${requestScope.order.calculatePrice()}
            </div>

            <div class="box">
                <h2>Filer: </h2>
            </div>

        </div>
        <a href="src/main/webapp/models/buildList-1.stl" download>download ordre</a>
        <a href="src/main/webapp/models/materialList-1.stl" download>download ordre</a>


    </jsp:body>

</t:pagetemplate>
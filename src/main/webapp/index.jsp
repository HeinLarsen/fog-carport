<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Bestil carport
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <h2>Order</h2>
            <div class="box">
                <c:if test="${sessionScope.order == null}">
                    <p>Her kan du se din fremtidige ordre. </p>

                </c:if>

                <c:if test="${sessionScope.order != null}">
                    <table class="table table-striped table-bordered">
                        <tr onclick="location.href='uservieworder?id=${order.id}'">
                            <td>${order.length} cm x ${order.width} cm, ${order.shed}</td>
                        </tr>
                    </table>
                </c:if>
            </div>
        </c:if>

        <form action="order" method="post">
            <div class="align-center">
                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Carport bredde</option>
                            <option value="option1">300cm</option>
                            <option value="option2">250cm</option>
                            <option value="option3">600cm</option>
                        </select>
                    </div>
                </div>


                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Carport længde</option>
                            <option value="option1">480cm</option>
                            <option value="option2">550cm</option>
                            <option value="option3">780cm</option>
                        </select>
                    </div>
                </div>

                <h2>Skur</h2>
                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Skur bredde</option>
                            <option value="option1">200cm</option>
                            <option value="option2">250cm</option>
                            <option value="option3">280cm</option>
                        </select>
                    </div>
                </div>

                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Skur længde</option>
                            <option value="option1">100cm</option>
                            <option value="option2">150cm</option>
                            <option value="option3">200cm</option>
                        </select>
                    </div>
                </div>

                <button class="btn btn-danger" type="submit">Bestil</button>

            </div>
        </form>


        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.roleId}".</p>
        </c:if>


    </jsp:body>

</t:pagetemplate>
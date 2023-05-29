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

                <div class="box homepage_grid_box box_width">
                <h3>Order liste:</h3>
                <div class="scrollable-table">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th class="sticky-header" style="background-color: lightgrey">ID</th>
                        <th class="sticky-header" style="background-color: lightgrey">Order</th>
                        <th class="sticky-header" style="background-color: lightgrey">Status</th>
                    </tr>
                    <c:forEach items="${requestScope.orderList}" var="order">
                        <tr onclick="location.href='uservieworder?id=${order.orderID}'">
                            <td>${order.length} cm x ${order.width} cm, ${order.shed}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            </div>
        </c:if>


        <form action="order" method="post">
            <div class="align-center">
                <div class="dropdown-container">
                    <div class="dropdown">
                        <select name="carportwidth">
                           <option value="" selected disabled> Carport bredde</option>
                            <c:forEach items="${requestScope.width}" var="width">
                                <option value="${width}" <c:if test="${width == requestScope.carportwidth}">selected</c:if> >${width} cm</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>


                <div class="dropdown-container">
                    <div class="dropdown">
                        <select name="carportlength">
                             <option value="" selected disabled> Carport længde</option>
                            <c:forEach items="${requestScope.length}" var="length">
                                <option value="${length}"  <c:if test="${length == requestScope.carportlength}">selected</c:if> >${length} cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <h2>Skur</h2>
                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Skur bredde</option>
                            <option value="option1">200cm</option>
                            <option value="option1">220cm</option>
                            <option value="option2">240cm</option>
                            <option value="option1">260cm</option>
                            <option value="option1">280cm</option>
                        </select>
                    </div>
                </div>

                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Skur længde</option>
                            <option value="option1">100cm</option>
                            <option value="option1">120cm</option>
                            <option value="option2">140cm</option>
                            <option value="option1">160cm</option>
                            <option value="option1">180cm</option>
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
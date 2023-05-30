<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Bestil carport
    </jsp:attribute>

    <jsp:body>

        <div class="row justify-content-center">
            <c:if test="${sessionScope.user != null}">
                <div class="col-6">
                    <div class="box mt-4">
                        <h3>Order liste:</h3>
                        <div>
                            <c:if test="${sessionScope.userOrders == null}">
                                <p>Her kan du se din fremtidige ordre. </p>
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${sessionScope.userOrders != null}">
                                <div class="scrollable-table">
                                    <table class="table table-striped table-bordered">
                                        <tr>
                                            <th class="sticky-header" style="background-color: white">ID</th>
                                            <th class="sticky-header" style="background-color: white">Order</th>
                                            <th class="sticky-header" style="background-color: white">Status</th>
                                        </tr>
                                        <c:forEach items="${sessionScope.userOrders}" var="order">
                                            <tr onclick="location.href='uservieworder?id=${order.orderID}&userid=${order.userID}'">
                                                <td>${order.orderID}</td>
                                                <td>${order.length} cm x ${order.width} cm, ${order.shed}</td>
                                                <td>${order.status}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="col-6">
                <form class="mt-5" action="order" method="post">
                    <div class="align-center">
                        <div class="dropdown-container">
                            <h4>Carport bredde</h4>
                            <div class="dropdown">
                                <select name="carportwidth">
                                    <c:forEach items="${requestScope.carportWidthList}" var="width">
                                        <option value="${width}"
                                                <c:if test="${width == requestScope.carportwidth}">selected</c:if>>${width}
                                            cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="dropdown-container">
                            <h4>Carport længde</h4>
                            <div class="dropdown">
                                <select name="carportlength">
                                    <c:forEach items="${requestScope.carportLengthList}" var="length">
                                        <option value="${length}"
                                                <c:if test="${length == requestScope.carportlength}">selected</c:if>>${length}
                                            cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <h2 class="mt-5">Skur</h2>
                        <div class="dropdown-container">
                            <h4>Skur bredde</h4>
                            <div class="dropdown">
                                <select name="shedwidth">
                                    <c:forEach items="${requestScope.shedWidthList}" var="shedwidth">
                                        <option value="${shedwidth}"
                                                <c:if test="${shedwidth == requestScope.shedwidth}">selected</c:if>>${shedwidth}
                                            cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="dropdown-container">
                            <h4>Skur længde</h4>
                            <div class="dropdown">
                                <select name="shedlength">
                                    <c:forEach items="${requestScope.shedLengthList}" var="shedlength">
                                        <option value="${shedlength}"
                                                <c:if test="${shedlength == requestScope.shedlength}">selected</c:if>>${shedlength}
                                            cm
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button class="btn btn-danger" type="submit">Bestil</button>
                    </div>
                </form>
            </div>
        </div>



    </jsp:body>

</t:pagetemplate>
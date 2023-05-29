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
                            <option value="option1">320cm</option>
                            <option value="option2">340cm</option>
                            <option value="option1">360cm</option>
                            <option value="option1">380cm</option>
                            <option value="option1">400cm</option>
                            <option value="option1">420cm</option>
                            <option value="option1">440cm</option>
                            <option value="option1">460cm</option>
                            <option value="option1">480cm</option>
                            <option value="option1">500cm</option>
                            <option value="option1">520cm</option>
                            <option value="option1">540cm</option>
                            <option value="option1">560cm</option>
                            <option value="option1">580cm</option>
                            <option value="option3">600cm</option>
                        </select>
                    </div>
                </div>


                <div class="dropdown-container">
                    <div class="dropdown">
                        <select>
                            <option value="" selected disabled> Carport længde</option>
                            <option value="option1">480cm</option>
                            <option value="option1">500cm</option>
                            <option value="option2">520cm</option>
                            <option value="option1">540cm</option>
                            <option value="option1">560cm</option>
                            <option value="option1">580cm</option>
                            <option value="option1">600cm</option>
                            <option value="option1">620cm</option>
                            <option value="option1">640cm</option>
                            <option value="option1">660cm</option>
                            <option value="option1">680cm</option>
                            <option value="option1">700cm</option>
                            <option value="option1">720cm</option>
                            <option value="option1">740cm</option>
                            <option value="option1">760cm</option>
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
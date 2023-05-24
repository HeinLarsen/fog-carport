    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>



    <jsp:body>


        <div>
            <t2> Bestillings form </t2>
                <div class="dropdown">
                    <select>
                        <option value="" selected disabled> Carport bredde</option>
                        <option value="option1">200cm</option>
                        <option value="option2">250cm</option>
                        <option value="option3">300cm</option>
                </select>
            </div>

            <div>
                <div class="dropdown">
                    <select>
                        <option value="" selected disabled> Carport længde</option>
                        <option value="option1">500cm</option>
                        <option value="option2">550cm</option>
                        <option value="option3">600cm</option>
                    </select>
                </div>

            </div>

            <div>
                <div class="dropdown">
                    <select>
                        <option value="" selected disabled> Carport højde</option>
                        <option value="option1">400cm</option>
                        <option value="option2">450cm</option>
                        <option value="option3">500cm</option>
                    </select>
                </div>

            </div>


        </div>




        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.roleId}".</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>
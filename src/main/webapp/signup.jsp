<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         Sign up
    </jsp:attribute>

    <jsp:body>
        <form action="signup" method="post">
            <div class="row justify-content-center mt-5">
                <div class="email col-4">
                    <label for="email">Email:</label>
                    <input class="m_right" type="text" id="email" name="email">
                </div>

                <div class="password col-4">
                    <label for="password">Password:</label>
                    <input class="m_right" type="password" id="password" name="password">
                </div>

                <div class="address col-4">
                    <label for="address">Address:</label>
                    <input class="m-right" type="text" id="address" name="address">
                </div>

                <div class="first_name col-4">
                    <label for="first_name">First Name:</label>
                    <input class="r_left" type="text" id="first_name" name="first_name">
                </div>

                <div class="last_name col-4">
                    <label for="last_name">Last Name:</label>
                    <input class="m_left" type="text" id="last_name" name="last_name">
                </div>

                <div class="phone_number col-4">
                    <label for="phone_number">Phone Number:</label>
                    <input class="m_left" type="text" id="phone_number" name="phone_number">
                </div>

                <div class="role col-4">
                    <label for="role">Role:</label>
                    <input class="m_left" type="text" id="role" name="role">
                </div>

                <div class="membership col-4">
                    <label for="membership">Membership:</label>
                    <input class="m_left" type="text" id="membership" name="membership">
                </div>

                <div class="signup col-8">
                    <button type="submit" class="btn btn-success">Sign Up</button>
                </div>
            </div>
        </form>

        
    </jsp:body>

</t:pagetemplate>
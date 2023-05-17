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


                <div class="first_name col-4">
                    <label for="first_name">Fornavn:</label>
                    <input class="r_left" type="text" id="first_name" name="first_name">
                </div>


                <div class="last_name col-4">
                    <label for="last_name">Efternavn:</label>
                    <input class="m_left" type="text" id="last_name" name="last_name">
                </div>



                <div class="email col-4">
                    <label for="email">Email:</label>
                    <input class="m_right" type="text" id="email" name="email">
                </div>


                <div class="password col-4">
                    <label for="password">Adgangskode:</label>
                    <input class="m_right" type="password" id="password" name="password">
                </div>

                <div class="address col-4">
                    <label for="address">Addresse:</label>
                    <input class="m-right" type="text" id="address" name="address">
                </div>


                <div class="phone_number col-4">
                    <label for="phone_number">Telefon Nummer:</label>
                    <input class="m_left" type="text" id="phone_number" name="phone_number">
                </div>

                <div class="zip col-4">
                    <label for="zip">by:</label>
                    <input class="m_left" type="text" id="zip" name="zip">
                </div>

                <div class="signup col-8">
                    <button type="submit" class="btn btn-success">Opret</button>
                </div>
            </div>
        </form>


    </jsp:body>

</t:pagetemplate>
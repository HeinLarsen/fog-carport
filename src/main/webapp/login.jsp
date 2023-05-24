<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Login
    </jsp:attribute>


    <jsp:body>



        <form class="box signup_form" action="login" method="post">

            <div class="row justify-content-center mt-5">
                <div class=" col-5">
                    <label for="email">Email: </label>
                    <input type="text" id="email" name="email"/>
                </div>

                <div class="col-5">
                    <label for="password">Adgangskode: </label>
                    <input type="password" id="password" name="password"/>
                </div>

                <div class="login col-3">
                    <input type="submit" class="btn btn-primary mb-2" value="Log in"/>
                </div>

            </div>


            <div class="row justify-content-center mt-3">

                <div class="signupKnap col-auto">
                    <p class="d-inline">Du kan oprette dig her: </p>
                        <a href="signup.jsp" class="btn btn-primary mb-2">Opret profil</a>
                    </p>
                </div>

            </div>

        </form>

    </jsp:body>
</t:pagetemplate>
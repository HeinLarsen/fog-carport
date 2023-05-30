<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
<jsp:attribute name="header">
         Order Menu
    </jsp:attribute>

  <jsp:body>

    <div class="mt-5 align-center griddy">
      <div class="box homepage_grid_box">
        <h2>Stykliste:</h2>
        <div class="scrollable-table">
          <table class="table table-striped table-bordered">
            <tr>
              <th class="sticky-header">Beskrivelse</th>
              <th class="sticky-header">Længde</th>
              <th class="sticky-header">Antal</th>
              <th class="sticky-header">Enhed</th>
              <th class="sticky-header">Beskrivelse</th>
            </tr>
            <tr>
              <th class="header">Træ</th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
            </tr>
            <c:forEach items="${requestScope.orderItemWood}" var="wood">
              <tr>
                <td>${wood.material.height}x${wood.material.width}cm. ${wood.material.category}</td>
                <td>${wood.material.length}</td>
                <td>${wood.quantity}</td>
                <td>${wood.material.unit}</td>
                <td>${wood.description}</td>
              </tr>
            </c:forEach>


            <tr>
              <th class="header">Tagplader</th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
            </tr>
            <c:forEach items="${requestScope.orderItemRoofTile}" var="rooftile">

              <tr>
                <td>${rooftile.material.name}</td>
                <td>${rooftile.material.length}</td>
                <td>${rooftile.quantity}</td>
                <td>${rooftile.material.unit}</td>
                <td>${rooftile.description}</td>
              </tr>
            </c:forEach>


            <tr>
              <th class="header">Skruer</th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
            </tr>
            <c:forEach items="${requestScope.orderItemScrew}" var="screw">
              <tr>
                <td>${screw.material.name}</td>
                <td></td>
                <td>${screw.quantity}</td>
                <td>${screw.material.unit}</td>
                <td>${screw.description}</td>
              </tr>
            </c:forEach>

                        <tr>

            <tr>
              <th class="header">Beslag</th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
              <th class="header"></th>
            </tr>
            <c:forEach items="${requestScope.orderItemFitting}" var="fitting">
              <tr>
                <td>${fitting.material.name}</td>
                <td>${fitting.material.length} ${fitting.material.unit}</td>
                <td>${fitting.material.price}</td>
                <td>${fitting.quantity}</td>
                <td>${fitting.description}</td>
              </tr>

            </c:forEach>

          </table>

        </div>

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

      <form action="admineditorder" method="post">
        <div class="box">
          <h2>Kontrolpanel:</h2>
          <button type="submit" name="status" value="APPROVED">Godkend</button>
          <button type="submit">Annuller</button>
          <button type="submit">Slet ordre</button>
        </div>
      </form>

      <div class="box">
        <h2>Filer:
          <a href="${pageContext.request.contextPath}/webapp/models/buildList-${requestScope.orderbyid.orderID}.stl" download>download buildlist</a>
          <a href="${pageContext.request.contextPath}/webapp/models/materialList-${requestScope.orderbyid.orderID}.stl" download>download matrialeliste</a>
        </h2>
      </div>
    </div>



  </jsp:body>
</t:pagetemplate>

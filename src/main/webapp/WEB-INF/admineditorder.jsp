<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordre menu</title>
</head>
<body>

<div class="box">
    <br>
    <t2>Bruger info:</t2>
    <br>
    Navn: ${user.firstName} ${user.lastName}
    <br>
    Email: ${user.email}
    <br>
    Telefon nummer: ${user.phoneNumber}
    <br>
    Adresse: ${user.address}
    <br>
    Postnummer: ${user.zip}
    <br>
    Medlemskab: ${user.membership}
    <br/>
</div>


<t2>Stykliste:<t2/>

    <div class="box">
        ${order.getOrderItems()}
    </div>

    <div class="box"></div>
    <button type="submit">Godkend </button>
    <button type="submit">Annuller </button>
    <button type="submit">Slet ordre </button>
</body>
</html>

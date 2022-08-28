
<!DOCTYPE HTML>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
    <title>Welcome</title>
</head>
<body>
<header>
    <jsp:include page="/menu.jsp"></jsp:include>
</header>
<div class="tab">
    <button class="tablinks" onclick="openCity(event, 'Welcome')" id="defaultOpen"> <b><fmt:message key="welcome"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'Benefits')"> <b><fmt:message key="benefits"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'Admin')"><b><fmt:message key="admin"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'Cashier')"><b><fmt:message key="cashier"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'SeniorCashier')"><b><fmt:message key="senior_cashier"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'Expert')"> <b><fmt:message key="commodity_expert"/>:</b></button>
    <button class="tablinks" onclick="openCity(event, 'Owner')"> <b><fmt:message key="owner"/>:</b></button>
</div>

<div id="Welcome" class="tabcontent">
    <div class="hor">
    <h2 style="text-align: center"><b><fmt:message key="welcome_to_app"/></b></h2>
    <div class="wrapper">
    <img  class="back"
         src="https://cdn-icons-png.flaticon.com/512/94/94648.png" alt="grn">
    <img   class="front"
          src="https://cdn-icons-png.flaticon.com/512/94/94648.png" alt="grn">
    </div>
    <div class="test"></div>
</div>
</div>

<div id="Benefits" class="tabcontent">
    <ul>
        <li ><fmt:message key="Twolanguages"/></li>
        <li ><fmt:message key="allaccounts"/></li>
        <li ><fmt:message key="sumandprice"/></li>
        <li ><fmt:message key="10"/></li>
        <li ><fmt:message key="allclosedreceipts"/></li>
        <li ><fmt:message key="Zreporttruncates"/></li>
        <li ><fmt:message key="onlyclosedreceipts"/></li>
        <li ><fmt:message key="sortingandpaginating"/></li>
        <li ><fmt:message key="ReCaptcha"/></li>
        <li ><fmt:message key="ConnectionpoolApache"/></li>
        <li ><fmt:message key="Allinbackand"/></li>
        <li ><fmt:message key="Securityuser"/></li>
        <li ><fmt:message key="emailtoReset"/></li>
        <li ><fmt:message key="AbstractFactorypattern"/></li>
        <li ><fmt:message key="Connectiontodb"/></li>
        <li ><fmt:message key="Sessionandcookies"/></li>
        <li ><fmt:message key="Patriotic"/></li>
        <li ><fmt:message key="Notifications"/></li>
        <li ><fmt:message key="Allapp"/></li>
        <li ><fmt:message key="Allproductshave"/></li>
        <li ><fmt:message key="Eachuser"/></li>
    </ul>
</div>

<div id="Admin" class="tabcontent">
    <ul class="hor">
    <li ><i> <fmt:message key="Admincan"/></i></li>
            <li ><fmt:message key="Updateuser"/></li>
            <li ><fmt:message key="Deleteuser"/></li>
            <li ><fmt:message key="Archivereceipts"/></li>
            <li ><fmt:message key="Archivereceiptsinfo"/></li>
            <li ><fmt:message key="Reports"/></li>
    </ul>
</div>

<div id="Cashier" class="tabcontent">
    <ul class="hor">
        <li><i> <fmt:message key="Cashiercan"/></i></li>
        <li ><fmt:message key="Updateuser"/></li>
        <li ><fmt:message key="Addproducts"/></li>
        <li ><fmt:message key="Productinfo"/></li>
    </ul>
</div>

<div id="SeniorCashier" class="tabcontent">
    <ul class="hor">
        <li><i><fmt:message key="Seniorcashiercan"/></i></li>
        <li><fmt:message key="Updateuser"/></li>
        <li><fmt:message key="Productinfo"/></li>
        <li><fmt:message key="Inforeceipt"/></li>
        <li><fmt:message key="Deletereceipts"/></li>
        <li><fmt:message key="Cancelproducts"/></li>
        <li><fmt:message key="CreatereportX"/></li>
        <li><fmt:message key="CreatereportZ"/></li>
    </ul>
</div>

<div id="Expert" class="tabcontent">
    <ul class="hor" >
                <li><i><fmt:message key="Commodityexpertcan"/></i></li>
                <li ><fmt:message key="Updateuser"/></li>
                <li ><fmt:message key="Createproduct"/></li>
                <li ><fmt:message key="Infoproduct"/></li>
                <li ><fmt:message key="Updateproduct"/></li>
                <li ><fmt:message key="Deleteproduct"/></li>
            </ul>
</div>
<div id="Owner" class="tabcontent">
    <ul class="hor" >
                <li><i><fmt:message key="Wasmade"/></i></li>

            </ul>
</div>
<script>
    <%@include file="script.js" %>
</script>

</body>
</html>
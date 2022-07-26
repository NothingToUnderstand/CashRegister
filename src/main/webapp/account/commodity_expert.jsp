<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="styleAcc.css" %>
    </style>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages"/>
</head>
<body>

<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<div class="sidenav">

    <b><fmt:message key="commodity_expert"/>:</b>
    <hr class="hl">
    <p>${user.getFullName()}</p>
    <p>Id: ${user.getId()}</p>
    <p><fmt:message key="userpassword"/>: ${user.getPassword()}</p>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/create/product"><fmt:message key="createproduct"/></a>
</div>
<div class="main">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="image"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="quantity"/></th>
            <th><fmt:message key="info"/></th>
            <th><fmt:message key="update"/></th>
            <th><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${product}" var="product">
            <tr>
                <td><img alt="product image" src="data:image/jpg;base64, ${product.getImgbase64()}" title="product image" style="height:30px;width: 30px;"></td>
                <td>${product.getName()}</td>
                <td>${product.getQuantity()}</td>
                <td><a class="btn btn-info"
                       href="${pageContext.request.contextPath}/info/product?id=${product.getId()}"><fmt:message key="info"/></a></td>
                <td><a class="btn btn-warning"
                       href="${pageContext.request.contextPath}/update/product?id=${product.getId()}&name=${product.getName()}
           &quantity=${product.getQuantity()}&weight=${product.getWeight()}&price=${product.getPrice()}"><fmt:message key="update"/></a></td>
                <td><a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/delete/product?id=${product.getId()}"><fmt:message key="delete"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

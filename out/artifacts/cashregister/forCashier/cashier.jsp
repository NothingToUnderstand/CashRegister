<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../css/styleForAccounts.css" %>
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
    <h3><fmt:message key="cashier"/>:</h3>
    <hr class="hl">
    <p>${user.getFullName()}</p>
    <p>Id: ${user.getId()}</p>
    <p><fmt:message key="email"/>: ${user.getEmail()}</p>

    <hr class="hl">
    <a href="${pageContext.request.contextPath}/update/user?id=${user.getId()}"><fmt:message key="updateuser"/></a>
    <hr class="hl">
    <c:if test="${receipt.getId()==null}">
        <form method="post" action="${pageContext.request.contextPath}/create/receipt?id=${receipt.getId()}">
            <button type="submit"><fmt:message key="createreceipt"/></button>
        </form>
    </c:if>
    <c:if test="${receipt.getId()!=null}">


        <form method="post"
              action="${pageContext.request.contextPath}/all/products?receiptid=${receipt.getId()}">
            <button type="submit"><fmt:message key="addproducttoreceipt"/></button>
        </form>


        <form method="post" action="${pageContext.request.contextPath}/close/receipt?id=${receipt.getId()}">
            <button type="submit"><fmt:message key="closereceipt"/></button>
        </form>
    </c:if>
</div>

<div class="main">
    <c:if test="${receipt==null}">
        <div class="alert alert-warning" role="alert">
            <fmt:message key="createreceipt"/>
        </div>
    </c:if>
    <c:if test="${receipt!=null}">

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>ID:</th>
            <th><fmt:message key="cahierid"/></th>
            <th><fmt:message key="cahiername"/></th>
            <th><fmt:message key="numberofproducts"/></th>
            <th><fmt:message key="totalsum"/></th>
            <th><fmt:message key="opened"/></th>
            <th><fmt:message key="info"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${receipt.getId()}</td>
            <td>${receipt.getCashierId()}</td>
            <td>${receipt.getCashierName()}</td>
            <td>${receipt.getNumberOfProducts()}</td>
            <td>${receipt. getTotalSum()}</td>
            <td>${receipt.getOpenDate()}</td>
            <td><a class="btn btn-info"
                   href="${pageContext.request.contextPath}/info/receipt?id=${receipt.getId()}"><fmt:message key="info"/></a></td>        </tr>
        </tbody>
    </table>

</div>
</c:if>
</body>
</html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<style>
    <%@include file="../css/product.css" %>
</style>
<body>
<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<h2><b><fmt:message key="productinfo"/></b></h2>

<table style="width: 50%" class="table">
    <tr>
        <td class="title">ID :</td>
        <td class="option">${product.getId()}</td>
    </tr>

        <tr>
        <td class="title"><fmt:message key="image"/>:</td>
        <td class="option"><img alt="product image" src="data:image/jpeg;base64,${product.getImgbase64()}" title="product image" style="height:50px;width: 50px;"></td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="name"/> :</td>
        <td class="option">${product.getName()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="quantity"/> :</td>
        <td class="option">${product.getQuantity()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="weight"/> :</td>
        <td class="option">${product.getWeight()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="price"/> :</td>
        <td class="option">${product.getPrice()}</td>
    </tr>
    <tr>
        <td colspan="3">
            <c:if test="${user.getRole()=='senior_cashier'}">
                <a class="btn btn-secondary btn-block" href="${pageContext.request.contextPath}/info/receipt" ><fmt:message key="back"/> </a>
            </c:if>
            <c:if test="${user.getRole()=='cashier'}">
            <a class="btn btn-secondary btn-block" href="${pageContext.request.contextPath}/all/products" ><fmt:message key="back"/> </a>
            </c:if>
            <c:if test="${user.getRole()=='commodity_expert'}">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/acc/commodity_expert" ><fmt:message key="back"/> </a>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/product?id=${product.getId()}"><fmt:message key="update"/> </a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/product?id=${product.getId()}"><fmt:message key="delete"/> </a>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
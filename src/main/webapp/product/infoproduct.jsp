<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<body>
<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<h2><fmt:message key="productinfo"/> </h2>

<table border="0">
    <tr>
        <td>ID :</td>
        <td>${product.getId()}</td>
    </tr>

        <tr>
        <td><fmt:message key="image"/>:</td>
        <td><img alt="product image" src="data:image/jpeg;base64,${product.getImgbase64()}" title="product image" style="height:30px;width: 30px;"></td>
    </tr>
    <tr>
        <td><fmt:message key="name"/> :</td>
        <td>${product.getName()}</td>
    </tr>
    <tr>
        <td><fmt:message key="quantity"/> :</td>
        <td>${product.getQuantity()}</td>
    </tr>
    <tr>
        <td><fmt:message key="weight"/> :</td>
        <td>${product.getWeight()}</td>
    </tr>
    <tr>
        <td><fmt:message key="price"/> :</td>
        <td>${product.getPrice()}</td>
    </tr>
    <tr>
        <td colspan="3">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/acc/commodity_expert" ><fmt:message key="back"/> </a>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/product?id=${product.getId()}"><fmt:message key="update"/> </a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/product?id=${product.getId()}"><fmt:message key="delete"/> </a>
        </td>
    </tr>
</table>
</body>
</html>
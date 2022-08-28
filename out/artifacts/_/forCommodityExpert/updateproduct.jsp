<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
<h2><b><fmt:message key="productupdate"/> </b></h2>
<form method="POST" action="${pageContext.request.contextPath}/update/product" enctype="multipart/form-data">
    <table style="width: 30%" class="table">
        <tr>
            <td class="title">ID:</td>
            <td ><input class="read"  name="id" value="${product.getId()}" readonly /></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="name"/> :</td>
            <td ><input class="read" type="text" name="name" value="${product.getName()}" readonly/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="quantity"/> :</td>
            <td ><input type="text" name="quantity" value="${product.getQuantity()}" required pattern="^[0-9]+$"/></td>
            <td>1</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="weight"/> :</td>
            <td  ><input type="text" name="weight" value="${product.getWeight()}" required pattern="^[0-9]+(\.[0-9]{1})?"/></td>
        <td>1.1</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="price"/> :</td>
            <td ><input  type="text" name="price" value="${product.getPrice()}"/></td>
            <td>1.1</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="image"/> :</td>
            <td ><input type="file" name="img" value="${product.getImgbase64()}" size="2" required  placeholder="Product img" title="Product img"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="<fmt:message key="submit"/> "/>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/acc"><fmt:message key="cancel"/> </a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

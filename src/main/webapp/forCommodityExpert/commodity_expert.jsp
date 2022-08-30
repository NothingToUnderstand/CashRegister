<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <style>
        <%@include file="../css/styleForAccounts.css" %>
    </style>

</head>
<body>

<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<div class="sidenav">

    <h3><fmt:message key="commodity_expert"/>:</h3>
    <hr class="hl">
    <p>${loginedUser.getFullName()}</p>
    <p>Id: ${loginedUser.getId()}</p>
    <p style="font-size: 15px"><fmt:message key="email"/>: ${loginedUser.getEmail()}</p>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/update/user?id=${loginedUser.getId()}"><fmt:message key="updateuser"/></a>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/create/product"><fmt:message key="createproduct"/></a>
</div>




<div class="main">

    <b><fmt:message key="totalamount"/>: ${amount}</b>

    <select class="form-control-sm" size="1" name="java-navigator" onchange="top.location.href =
  this.options[this.selectedIndex].value;">
        <option selected disabled>№</option>
        <option class="ui-icon" value="?page=1&perpage=2">2</option>
        <option  class="ui-icon selected" value="?page=1&perpage=5">5</option>
        <option class="ui-icon" value="?page=1&perpage=10">10</option>
    </select>
    <div class="form-inline" style="float: right;">
        <form action="${pageContext.request.contextPath}/acc" method="post">
            <input style="width:120px;" class="form-control form-control-sm" type="text" placeholder="<fmt:message key="search"/>.." name="forsearch">
            <button style="width:80px;" class="btn btn-primary btn-sm" type="submit"><fmt:message key="search"/></button>
        </form>
    </div>
    <c:if test="${search!=null}">
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
            <tr>
                <td><img alt="product image" src="data:image/jpg;base64, ${search.getImgbase64()}" title="product image"
                         style="height:30px;width: 30px;"></td>
                <td>${search.getName()}</td>
                <td>${search.getQuantity()}</td>
                <td><a class="btn btn-info"
                       href="${pageContext.request.contextPath}/info/product?id=${search.getId()}"><fmt:message
                        key="info"/></a></td>
                <td><a class="btn btn-warning"
                       href="${pageContext.request.contextPath}/update/product?id=${search.getId()}"><fmt:message
                        key="update"/></a></td>
                <td><a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/delete/product?id=${search.getId()}"><fmt:message
                        key="delete"/></a></td>
            </tr>
            </tbody>
        </table>
    </c:if>
    <c:if test="${search==null}">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="image"/></th>
            <th><a href="?col=name&dir=${dir}"><fmt:message key="name"/></a></th>
            <th><a href="?col=quantity&dir=${dir}"><fmt:message key="quantity"/></a></th>
            <th><fmt:message key="info"/></th>
            <th><fmt:message key="update"/></th>
            <th><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="product">
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
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:if test="${page >1}">
                    <a  class="page-link" href="?page=${page-1}&perpage=${perpage}" tabindex="-1">&laquo;</a>
                </c:if>
            </li>
            <c:forEach begin="1" end="${numpage}" var="i">
                <li class="page-item"><a class="page-link"
                                         href="?page=${i}&perpage=${perpage}">${i}</a></li>
            </c:forEach>
            <li>
                <c:if test="${page <numpage}">
                    <a class="page-link" href="?page=${page+1}&perpage=${perpage}">&raquo;</a>
                </c:if>
            </li>
        </ul>
    </nav>
    </c:if>
</div>
</body>
</html>

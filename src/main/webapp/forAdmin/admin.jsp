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

    <h3><fmt:message key="admin"/>:</h3>
    <hr class="hl">
    <p>${user.getFullName()}</p>
    <p>Id: ${user.getId()}</p>
    <p><fmt:message key="userpassword"/>: ${user.getPassword()}</p>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/update/user?id=${user.getId()}"><fmt:message key="updateuser"/></a>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/archive/receipts"><fmt:message key="archivereceipts"/></a>
    <a href="${pageContext.request.contextPath}/all/reports"><fmt:message key="allreports"/></a>





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
        <form action="${pageContext.request.contextPath}/archive/receipts" method="post">
            <input  style="width:120px;" class="form-control form-control-sm" type="text" placeholder="<fmt:message key="search"/>.." value="${id}" name="id">
            <button style="width:80px;" class="btn btn-primary btn-sm" type="submit"><fmt:message key="search"/></button>
        </form>
    </div>
    <c:if test="${search!=null}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th><fmt:message key="fullname"/></th>
                <th><fmt:message key="role"/></th>
                <th><fmt:message key="update"/></th>
                <th><fmt:message key="delete"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <c:forEach items="${users}" var="u" >
            <tr>
                <td>${u.getId()}</td>
                <td>${u.getFullName()}</td>
                <td>${u.getRole()}</td>
                <td> <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/user?id=${u.getId()}" ><fmt:message key="update"/></a></td>
                <td> <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/user?id=${u.getId()}"><fmt:message key="delete"/></a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${search==null}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th><a href="?col=id&dir=${dir}">ID</a></th>
                <th><a href="?col=full_name&dir=${dir}"><fmt:message key="fullname"/></a></th>
                <th><fmt:message key="role"/></th>
                <th><fmt:message key="update"/></th>
                <th><fmt:message key="delete"/></th>
            </thead>
            <tbody>
            <tr>
                <c:forEach items="${users}" var="u" >
            <tr>
                <td>${u.getId()}</td>
                <td>${u.getFullName()}</td>
                <td>${u.getRole()}</td>
                <td> <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/user?id=${u.getId()}" ><fmt:message key="update"/></a></td>
                <td> <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/user?id=${u.getId()}"><fmt:message key="delete"/></a></td>
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

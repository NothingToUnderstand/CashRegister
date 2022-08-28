<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<div class="main">
    <a class="btn btn-secondary btn-xs" href="${pageContext.request.contextPath}/acc" >
        <fmt:message key="back"/> </a>
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
        <input pattern="^[ 0-9]+$" style="width:120px;" class="form-control form-control-sm" type="number" placeholder="<fmt:message key="search"/>.." value="${id}" name="id">
        <button style="width:80px;" class="btn btn-primary btn-sm" type="submit"><fmt:message key="search"/></button>
    </form>
</div>
<c:if test="${search!=null}">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="cahierid"/></th>
            <th><fmt:message key="cahiername"/></th>
            <th><fmt:message key="numberofproducts"/></th>
            <th><fmt:message key="totalsum"/></th>
            <th><fmt:message key="opened"/></th>
            <th><fmt:message key="closed"/></th>
            <th><fmt:message key="info"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${search.getCashierId()}</td>
            <td>${search.getCashierName()}</td>
            <td>${search.getNumberOfPoducts()}</td>
            <td>${search.getTotalSum()}</td>
            <td>${search.getOpenDate()}</td>
            <td>${search.getCloseDate()}</td>
            <td><a class="btn btn-info"
                   href="${pageContext.request.contextPath}/info/archive/receipt?id=${search.getId()}"><fmt:message key="info"/></a></td>
        </tr>
        </tbody>
    </table>
</c:if>
<c:if test="${search==null}">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th><a href="?col=cashier_id&dir=${dir}"><fmt:message key="cahierid"/></a></th>
            <th><a href="?col=cashier_name&dir=${dir}"><fmt:message key="cahiername"/></a></th>
            <th><a href="?col=number_of_products&dir=${dir}"><fmt:message key="numberofproducts"/></a></th>
            <th><a href="?col=total_sum&dir=${dir}"><fmt:message key="totalsum"/></a></th>
            <th><a href="?col=open_date_time&dir=${dir}"><fmt:message key="opened"/></a></th>
            <th><a href="?col=close_date_time&dir=${dir}"><fmt:message key="closed"/></a></th>
            <th><fmt:message key="info"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${receipt}" var="receipt">
            <tr>
                <td>${receipt.getCashierId()}</td>
                <td>${receipt.getCashierName()}</td>
                <td>${receipt.getNumberOfProducts()}</td>
                <td>${receipt. getTotalSum()}</td>
                <td>${receipt.getOpenDate()}</td>
                <td>${receipt.getCloseDate()}</td>
                <td><a class="btn btn-info"
                       href="${pageContext.request.contextPath}/info/archive/receipt?id=${receipt.getId()}"><fmt:message key="info"/></a></td>
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
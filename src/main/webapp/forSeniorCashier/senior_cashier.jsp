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
    <h3><fmt:message key="senior_cashier"/>:</h3>
    <hr class="hl">
    <p>${user.getFullName()}</p>
    <p>Id: ${user.getId()}</p>
    <p style="font-size: 15px"><fmt:message key="admin"/>: ${user.getEmail()}</p>
    <hr class="hl">
    <a href="${pageContext.request.contextPath}/update/user?id=${user.getId()}"><fmt:message key="updateuser"/></a>
    <hr class="hl">
        <form method="post" action="${pageContext.request.contextPath}/created/report?type=X">
            <button type="submit"><fmt:message key="createrereport"/> X</button>
        </form>
        <form method="post"
              action="${pageContext.request.contextPath}/created/report?type=Z">
            <button type="submit"><fmt:message key="createrereport"/> Z</button>
        </form>
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
            <input pattern="^[ 0-9]+$" style="width:120px;" class="form-control form-control-sm" type="number" placeholder="<fmt:message key="search"/>.."  name="forsearch">
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
                <th><fmt:message key="delete"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${search.getCashierId()}</td>
                <td>${search.getCashierName()}</td>
                <td>${search.getNumberOfProducts()}</td>
                <td>${search.getTotalSum()}</td>
                <td>${search.getOpenDate()}</td>
                <td>${search.getCloseDate()}</td>
                <td><a class="btn btn-info"
                       href="${pageContext.request.contextPath}/info/receipt?id=${search.getId()}"><fmt:message key="info"/></a></td>
                <c:if test="${search.getCloseDate()==null}">

                <td><a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/delete/receipt?id=${search.getId()}"><fmt:message key="delete"/></a></td>
                </c:if>
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
                <th><fmt:message key="delete"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${items}" var="i">
                <tr>
                    <td>${i.getCashierId()}</td>
                    <td>${i.getCashierName()}</td>
                    <td>${i.getNumberOfProducts()}</td>
                    <td>${i. getTotalSum()}</td>
                    <td>${i.getOpenDate()}</td>
                    <c:if test="${i.getCloseDate()!=null}">
                    <td>${i.getCloseDate()}</td>
                    </c:if>
                    <c:if test="${i.getCloseDate()==null&&i.getNumberOfProducts()!=0}">
                        <td>
                           <form action="${pageContext.request.contextPath}/close/receipt" method="post">
                               <input type="number" value="${i.getId()}" name="id" hidden readonly>
                               <button class="btn btn-success" type="submit"><fmt:message key="close"/></button>
                           </form>
                        </td>
                    </c:if>
                    <c:if test="${i.getCloseDate()==null&&i.getNumberOfProducts()==0}">
                        <td></td>
                    </c:if>
                    <td><a class="btn btn-info"
                           href="${pageContext.request.contextPath}/info/receipt?id=${i.getId()}"><fmt:message key="info"/></a></td>
                    <c:if test="${i.getCloseDate()==null}">
                    <td><a class="btn btn-danger"
                           href="${pageContext.request.contextPath}/delete/receipt?id=${i.getId()}"><fmt:message key="delete"/></a></td>
                    </c:if>

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
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<body>
<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<h2><fmt:message key="products"/>: </h2>
<div class="btn-group" role="group">
    <a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/acc"><fmt:message key="back"/></a>
    <select class="form-control-sm" size="1" name="java-navigator" onchange="top.location.href =
  this.options[this.selectedIndex].value;">
        <option selected disabled>№</option>
        <option class="ui-icon" value="?page=1&perpage=2">2</option>
        <option class="ui-icon selected" value="?page=1&perpage=5">5</option>
        <option class="ui-icon" value="?page=1&perpage=10">10</option>
    </select>

</div>
<b>Total amount of products: ${amount}</b>
<b>Receipt id: ${receiptid}</b>


<div class="form-inline" style="float: right;">
    <form action="${pageContext.request.contextPath}/all/products" method="post">
        <input class="form-control form-control-sm" type="text" placeholder="<fmt:message key="search"/>.."
               value="${name}" name="name">
        <button class="btn btn-outline-primary btn-sm" type="submit"><fmt:message key="search"/></button>
    </form>
</div>

<c:if test="${search!=null}">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th><fmt:message key="image"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="quantityAtwarehouse"/></th>
            <th><fmt:message key="quantitytoadd"/></th>
            <th><fmt:message key="info"/></th>
            <th><fmt:message key="addproducttoreceipt"/></th>
        </tr>
        </thead>
        <tbody>
        <form method="post" action="/add/product">
            <tr>
                <input name="receiptId" value="${receiptid}" type="hidden" readonly/>
                <td><input name="productId" value="${search.getId()}" style="width: 40px" readonly></td>
                <td><img alt="product image" src="data:image/jpg;base64, ${search.getImgbase64()}" title="product image"
                         style="height:30px;width: 30px;"></td>
                <td>${search.getName()}</td>
                <td>${search.getQuantity()}</td>
                <td><input name="quantity" value="1" type="number"
                           max="${product.getQuantity()}" min="1" onkeypress="return false" style="width: 50px"></td>
                <td><button class="btn btn-success"
                            type="submit"><fmt:message
                            key="addproducttoreceipt"/></button></td>
                <td><a class="btn btn-info"
                       href="${pageContext.request.contextPath}/info/product?id=${search.getId()}"><fmt:message
                        key="info"/></a></td>
            </tr>
        </form>
        </tbody>
    </table>
</c:if>
<c:if test="${search==null}">

    <table id="products" class="table">
        <thead class="thead-dark">
        <tr>
            <th><a href="?col=id&dir=${dir}">ID</a></th>
            <th><fmt:message key="image"/></th>
            <th><a href="?col=name&dir=${dir}"><fmt:message key="name"/></a></th>
            <th><a href="?col=quantity&dir=${dir}"><fmt:message key="quantityAtwarehouse"/></a></th>
            <th><fmt:message key="quantity"/></th>
            <th><fmt:message key="addproducttoreceipt"/></th>
            <th><fmt:message key="info"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${receipt}" var="product">
            <form method="post" action="/add/product">
                <tr>
                    <input name="receiptId" value="${receiptid}" type="hidden" readonly/>
                    <td><input name="productId" value="${product.getId()}" style="width: 40px" readonly></td>
                    <td><img alt="product image" src="data:image/jpg;base64, ${product.getImgbase64()}"
                             title="product image" style="height:30px;width: 30px;"></td>
                    <td>${product.getName()}</td>
                    <td>${product.getQuantity()}</td>
                    <td><input name="quantity" value="1" type="number"
                               max="${product.getQuantity()}" min="1" onkeypress="return false" style="width: 50px">
                    </td>
                    <td>
                        <button class="btn btn-success"
                                type="submit"><fmt:message
                                key="addproducttoreceipt"/></button>
                    </td>
                    <td><a class="btn btn-info"
                           href="${pageContext.request.contextPath}/info/product?id=${product.getId()}"><fmt:message
                            key="info"/></a></td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>


    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:if test="${page >1}">
                    <a class="page-link" href="?page=${page-1}&perpage=${perpage}" tabindex="-1">&laquo;</a>
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
</body>
</html>

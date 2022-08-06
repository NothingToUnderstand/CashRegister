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
<div style="margin-left:5%;float: left">
<h2><b><fmt:message key="receptinfo"/>:</b></h2>
<table style="display: inline-block;" class="table">
    <tr>
        <td class="title">ID :</td>
        <td class="option">${receipt.getId()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="cahierid"/>:</td>
        <td class="option">${receipt.getCashier_id()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="cahiername"/> :</td>
        <td class="option">${receipt.getCashier_name()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="numberofproducts"/> :</td>
        <td class="option">${receipt.getNumber_of_products()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="totalsum"/> :</td>
        <td class="option">${receipt.getTotal_sum()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="opened"/> :</td>
        <td class="option">${receipt.getOpenDate()}</td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="closed"/> :</td>
        <td class="option">${receipt.getCloseDate()}</td>
    </tr>
    <tr>
        <td colspan="3">
            <div class="btn-group btn-group-md" role="group">
            <a class="btn btn-secondary btn-block" href="${pageContext.request.contextPath}/acc/senior_cashier" ><fmt:message key="back"/> </a>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/receipt?id=${receipt.getId()}"><fmt:message key="delete"/> </a>
            </div>
        </td>
    </tr>
</table>
</div>
    <div style="margin-left:15%;width:50%;float: left">
        <h2><b><fmt:message key="productsinreceipt"/>:</b></h2>
<table style="float: left;" class="table">
    <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="number"/></th>
                    <th><fmt:message key="image"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="quantityatreceipt"/></th>
                    <th><fmt:message key="quantity"/></th>
                    <th><fmt:message key="info"/></th>
                    <th><fmt:message key="delete"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${receipt.getProductsInReceipt()}" var="product">
                    <form method="post" action="/cancel/product">
                        <input name="receiptid" value="${receipt.getId()}" type="hidden" readonly/>
                    <tr>
                        <td><input name="productid" value="${product.getId()}" style="width: 40px" readonly></td>
                        <td><input name="number" value="${product.getNumberInReceipt()}" style="width: 40px" readonly></td>
                        <td><img alt="product image" src="data:image/jpg;base64, ${product.getImgbase64()}" title="product image" style="height:30px;width: 30px;"></td>
                        <td>${product.getName()}</td>
                        <td><input name="atdb" value="${product.getQuantity()}" style="width: 40px" readonly/></td>
                        <td><input name="quantity" value="1" type="number"
                                   max="${product.getQuantity()}" min="1" onkeypress="return false" style="width: 50px">
                        </td>

                        <td><a class="btn btn-info"
                               href="${pageContext.request.contextPath}/info/product?id=${product.getId()}"><fmt:message key="info"/></a></td>
                        <c:if test="${receipt.getCloseDate()==null}">
                           <td> <button class="btn btn-danger"
                                    type="submit"><fmt:message
                                    key="delete"/></button></td>
                       </c:if>
                    </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
    </div>
</body>
</html>
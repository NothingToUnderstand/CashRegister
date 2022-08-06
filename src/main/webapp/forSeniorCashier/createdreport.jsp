<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
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
<div style="width: 50%">

        <h2 style="margin-left: 80%"><b><fmt:message key="createdreport"/>:</b></h2>
    <table style="margin-left: 50%" class="table">
        <tr>
            <td class="title">ID :</td>
            <td class="option">${report.getId()}</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="cahierid"/>:</td>
            <td class="option">${report.getCashier_id()}</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="cahiername"/> :</td>
            <td class="option">${report.getCashier_name()}</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="numberofreceipts"/> :</td>
            <td class="option">${report.getNumberOfReceipts()}</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="totalsum"/>:</td>
            <td class="option">${report.getTotalSum()}</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="date"/>:</td>
            <td class="option">${report.getDate()}</td>
        </tr>
        <tr>
            <td class="title">Z :</td>
            <td class="option">${report.isZReport()}</td>
        </tr>

        <tr>
            <td colspan="3">
                <div class="btn-group btn-group-md" role="group">
                    <a class="btn btn-secondary btn-block" href="${pageContext.request.contextPath}/acc/senior_cashier" ><fmt:message key="back"/> </a>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
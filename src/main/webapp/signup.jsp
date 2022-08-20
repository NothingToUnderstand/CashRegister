<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<style>
    <%@include file="css/product.css" %>
</style>
<body>
<header>
    <div>
        <jsp:include page="/menu.jsp"></jsp:include>
    </div>
</header>
<h2><fmt:message key="signup"/></h2>
<form method="POST" action="${pageContext.request.contextPath}/create/user">
    <table style=" width: 30%" class="table">
        <tr>
            <td class="title"><fmt:message key="firstname"/></td>
            <td ><input type="text" name="firstname" value="${firstname}" required pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
            <td>Aaa</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="lastname"/></td>
            <td ><input type="text" name="lastname" value="${lastname}" required pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
            <td>Aaa</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="password"/></td>
            <td >
                <input type="password" id="password-input" name="password"  required pattern="[A-ZА-ЯЁ]{1,}[A-Za-zА-Яа-яЁё]{3,}[0-9]{3,}"/>
                <a onclick="lock()"><img alt="show password" id="img" src="https://cdn-icons-png.flaticon.com/512/61/61457.png" width="20px" height="20px" /></a>
            </td>
            <td>Aaaa123</td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="role"/>:</td>
            <td>
                <select name="roleid" size="1" required>
                    <option value="1" selected><fmt:message key="cashier"/></option>
                    <option value="2"><fmt:message key="senior_cashier"/></option>
                    <option value="3"><fmt:message key="commodity_expert"/></option>
                    <option value="4"  ><fmt:message key="admin"/></option>
                </select>
            </td>
        </tr>
        <tr><td></td>
            <td colspan="3">
                <input class="btn btn-success" type="submit" value="Submit"/>
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/login"><fmt:message key="cancel"/></a>
            </td>
        </tr>   
    </table>
</form>
</body>
<script>
    <%@include file="script.js" %>
</script>
</html>

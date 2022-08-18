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
<h2><fmt:message key="updateuser"/>:</h2>
<form method="POST" action="${pageContext.request.contextPath}/update/user">
    <table  style=" width: 30%" class="table">
        <tr>
            <td class="title">ID:</td>
            <td><input name="id" value="${user.getId()}" readonly/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="firstname"/></td>
            <td><input type="text" name="firstname" value="${user.getFirstName()}" required
                       pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="lastname"/></td>
            <td><input type="text" name="lastname" value="${user.getLastName()}" required
                       pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="password"/></td>
            <td>
                <input type="password" id="password-input" name="password" value="${user.getPassword()}" required
                       pattern="[A-ZА-ЯЁ]{1,}[A-Za-zА-Яа-яЁё]{3,}[0-9]{3,}"/>
                <a onclick="lock()"><img alt="show password" id="img"
                                         src="https://cdn-icons-png.flaticon.com/512/61/61457.png" width="20px"
                                         height="20px"/></a>
            </td>
        </tr>
        <c:if test="${user.getRole()=='admin'}">
            <tr>
                <td class="title"><fmt:message key="role"/> :</td>
                <td><select name="roleid" size="1" required>
                    <option value="cashier"><fmt:message key="cashier"/></option>
                    <option value="senior_cashier"><fmt:message key="senior_cashier"/></option>
                    <option value="commodity_expert"><fmt:message key="commodity_expert"/></option>
                    <option value="admin"  selected><fmt:message key="admin"/></option>
                </select></td>
            </tr>
        </c:if>
        <c:if test="${user.getRole()!='admin'}">
            <td class="title"><fmt:message key="role"/> :</td>
            <td><input name="roleid" value="<fmt:message key="${user.getRole()}"/>" readonly/></td>
        </c:if>
        <tr>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="Submit"/>
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/acc"><fmt:message key="cancel"/></a>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    <%@include file="/script.js" %>
</script>
</html>

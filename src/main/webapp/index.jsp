<%@ page import="com.example.cashregister.entity.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE HTML>
<html>
<header>
    <jsp:include page="/menu.jsp"></jsp:include>
</header>

<h2>Hello</h2>
<body>
<div style="margin-top: 100px">
    <%
        User loginedUser = (User) request.getSession().getAttribute("loginedUser");
        if (loginedUser != null) {
            out.print("<span>Name: " + loginedUser.getFullName() + "</span>");
            out.print("<span> Id: " + loginedUser.getId() + "</span>");
            out.print("<span> Role: " + loginedUser.getRole() + "</span>");
        }
    %>
    <hr>

    <br>
<p>For translate</p>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="pleaseSignin"/></h1>
    <br>
    <hr>

</div>

</body>
</html>

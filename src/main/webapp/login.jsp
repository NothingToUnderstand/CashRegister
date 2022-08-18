<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src='https://www.google.com/recaptcha/api.js'></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<style>
    <%@include file="css/style.css" %>
</style>

<%
    Cookie[] cookies = request.getCookies();
    String fullName = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookname")) {
                fullName = cookie.getValue();
            }
        }
    }
%>
<html>
<head>
    <title><fmt:message key="signin"/></title>
</head>

<body class="text-center">
<header>
    <jsp:include page="/menu.jsp"></jsp:include>
</header>


<form class="form-signin" id="form-login" method="POST" action="${pageContext.request.contextPath}/login">
    <img class="mb-4"
         src="https://png.pngtree.com/png-vector/20191004/ourlarge/pngtree-cash-register-icon-png-image_1788618.jpg"
         alt="Cash Register" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="pleaseSignin"/></h1>

    <input type="text" id="inputUserName" placeholder="<fmt:message key="userfullname"/>" autofocus name="fullname"
           value="<%=fullName.replace("+"," ")%>" required>

    <input type="password" name="password" id="password-input" placeholder="<fmt:message key="userpassword"/>" required>
    <a onclick="lock()"><img alt="show password" id="img" src="https://cdn-icons-png.flaticon.com/512/61/61457.png"
                             width="20px" height="20px"/></a>


    <div id="captcha" class="g-recaptcha" data-sitekey="6LeafYQhAAAAADr5PgzznoTXh6D474PGwNyLaCN_"
         aria-required="true"></div>
    <label for="remember"><fmt:message key="rememberMe"/></label>
    <input id="remember" type="checkbox" value="remember-me" name="rememberMe"/>
    <br>
    <input type="submit" class="btn btn-lg btn-primary btn-md" value="<fmt:message key="signin"/>"/>
    <br>
    <span class="mt-5 mb-3 text-muted">&copy; 2022</span>
</form>
<p >Bob Bobchinski - Qqwe123 -cashier</p>
<p>Jack Nikson - Qqwe123 -commodity_expert</p>
<p>David Murchanski - Qqwe123 -senior_cashier</p>
<p>Tom Cat - Qqwe123 -admin</p>

<script>
    <%@include file="script.js" %>
</script>
</body>

</html>
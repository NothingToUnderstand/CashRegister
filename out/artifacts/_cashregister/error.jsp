<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src='https://www.google.com/recaptcha/api.js'></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<style>
    <%@include file="css/error.css" %>
</style>

<html>
<body>
<header>
    <jsp:include page="/menu.jsp"></jsp:include>
</header>
<div class="wrapper">
    <div class="box">
        <h1>500</h1>
        <p><fmt:message key="sorry"/></p>
        <p>&#58;&#40;</p>
        <p><a href="${pageContext.request.contextPath}/"><fmt:message key="tryagain"/></a></p>
    </div>
</div>
</body>
</html>

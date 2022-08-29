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
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<div class="wr">
    <h5><%= exception %></h5>
    <h5><%= message %></h5>
    <img style="width: 120px;height: 120px" src="https://www.dropbox.com/s/xq0841cp3icnuqd/flame.png?raw=1"/>
    <h3><fmt:message key="sorry"/></h3>
    <h3><a href="${pageContext.request.contextPath}/"><fmt:message key="tryagain"/></a></h3>
</div>


</body>
</html>

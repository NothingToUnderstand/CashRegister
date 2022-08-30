<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag.tld" prefix="tag"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>

    <style>
        <%@include file="css/style.css" %>
    </style>
    <title>Cash Register</title>
</head>

<body>
<div class="topnav">
    <a  style="font-size: 25px;margin-top: -5px" ><b>Cash Register:</b></a>
    <a class="vl"></a>
    <a href="/cashregister/"><img id="home" src="https://static.thenounproject.com/png/411768-200.png" width="30px" height="30px"
                     alt="home" title="Home"/></a>
    <a id="login" href="/cashregister/login"><img
            src="https://www.iconpacks.net/icons/2/free-user-login-icon-3057-thumb.png"
            width="30px" height="30px" alt="login" title="Login"/></a>
    <a id="signup" href="/cashregister/create/user"><img
            src="https://www.iconpacks.net/icons/2/free-user-signup-icon-3058-thumb.png"
            width="30px" height="30px" alt="sign up"
            title="Sign up"/></a>
    <a id="logout" href="/cashregister/logout"><img
            src="https://www.iconpacks.net/icons/2/free-user-logout-icon-3056-thumb.png"
            width="30px" height="30px" alt="logout"
            title="Logout"/></a>
    <a id="account" class="nav-link" href="/cashregister/acc"><img
            src="https://i.pinimg.com/originals/c7/ab/cd/c7abcd3ce378191a3dddfa4cdb2be46f.png" width="30px"
            height="30px" alt="account" title="Account"/></a>
    <a class="vl"></a>
    <input id="lu" type="number" hidden readonly value="${loginedUser.getId()}">
    <div class="center">
  <tag:MyTag></tag:MyTag>
    </div>
    <div class="topnav-right">
        <c:choose>
            <c:when test = "${lang =='ua'}">
        <a class="btn" id="eng"  onclick="eng()" type="submit"><img src="https://cdn-icons-png.flaticon.com/512/197/197374.png" width="30px" height="30px" alt="eng" title="eng"/></a>
            </c:when>
            <c:when test = "${lang == 'en'}">
        <a class="btn" id="ukr"  onclick="ukr()" type="submit"><img src="https://cdn-icons-png.flaticon.com/512/197/197572.png" width="30px" height="30px" alt="ukr" title="ukr"/></a>
            </c:when>
        </c:choose>
    </div>
</div>
<script>
    <%@include file="script.js" %>
</script>
</body>

</html>
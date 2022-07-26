<%@ page import="com.example.cashregister.entity.User" %>
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
    <br>
</div>

</body>
</html>

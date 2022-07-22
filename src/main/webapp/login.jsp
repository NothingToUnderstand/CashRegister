<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <title>Login</title>
</head>
<body>

<h3>Login Page</h3>

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

<form method="POST" action="${pageContext.request.contextPath}/login">
    <table border="0">
        <tr>
            <td>User Fullname</td>
            <td><input type="text" name="fullname" value="<%=fullName.replace("+"," ")%>" required pattern=
                    "^([A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{1,}\s{1})+([A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{1,})"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" required/></td>
        <tr>
            <td>Remember me:</td>
            <td><input type="checkbox" name="rememberMe"/></td>
        </tr>

    </table>
    <div class="g-recaptcha" data-sitekey="6LdAPwwhAAAAANIM60XreZO9B1YhhyeiXYWJL-qV"></div>
    <input type="submit" value="Submit" class="btn btn-success"/>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/">Cancel</a>
</form>
</body>
</html>
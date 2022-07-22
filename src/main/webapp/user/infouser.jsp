<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>User info:</h2>
<table border="0">
    <tr>
        <td>ID :</td>
        <td>${user.getId()}</td>
    </tr><tr>
    <td>Name:</td>
    <td>${user.getFullName()}</td>
</tr>
    <tr>
        <td>Password:</td>
        <td>${user.getPassword()}</td>
    </tr>
    <tr>
        <td>Role :</td>
        <td>${user.getRole()}</td>
    </tr>
    <tr>
        <td colspan="3">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/all/users" >Back</a>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/user?id=${user.getId()}">Update</a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/user?id=${user.getId()}">Delete</a>
        </td>
    </tr>
</table>
</body>
</html>
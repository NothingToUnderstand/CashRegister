
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>Users: </h2>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Role</th>
        <th>Info</th>
        <th>Update</th>
        <th>Delete</th>


    </tr>
    <c:forEach items="${users}" var="u" >
        <tr>
            <td>${u.getId()}</td>
            <td>${u.getFullName()}</td>
            <td>${u.getRole()}</td>
            <td> <a class="btn btn-info" href="${pageContext.request.contextPath}/info/user?id=${u.getId()}">Info</a></td>
            <td> <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/user?id=${u.getId()}&firstname=${u.getFirstName()}&lastname=${u.getLastName()}&role=${u.getRole()}" >Update</a></td>
            <td> <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/user?id=${u.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/" >Back</a>
</body>
</html>

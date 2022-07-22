<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>Create user:</h2>
<form method="POST" action="${pageContext.request.contextPath}/create/user">
    <table border="0">
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstname" value="${firstname}" required pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastname" value="${lastname}" required pattern="^[A-ZА-ЯЁ]{1}[A-Za-zА-Яа-яЁё]{2,}"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" value="${password}" required pattern="[A-Za-zА-Яа-яЁё]{3,}[0-9]{3,}"/></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <select name="roleid" size="1" required>
                    <option value="1" selected >cashier</option>
                    <option value="2">senior cashier</option>
                    <option value="3">commodity_expert</option>
                    <option value="4">admin</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="Submit"/>
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/all/users">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

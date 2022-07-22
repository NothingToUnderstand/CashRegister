<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>Update product:</h2>
<form method="POST" action="/update/product">
    <table border="0">
        <tr>
            <td>ID:</td>
            <td ><input  name="id" value= "<%=request.getParameter("id").replaceAll("\\s","")%>" readonly/></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="<%=request.getParameter("name").replaceAll("\\s","")%>" readonly/></td>
        </tr>
        <tr>
            <td>Quantity:</td>
            <td><input type="text" name="quantity" value="<%=request.getParameter("quantity").replaceAll("\\s","")%>" required pattern="^[0-9]+$"/></td>
        </tr>
        <tr>
            <td>Weight:</td>
            <td><input type="text" name="weight" value="<%=request.getParameter("weight").replaceAll("\\s","")%>" required pattern="^[0-9]+(\.[0-9]{1})?"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="<%=request.getParameter("price").replaceAll("\\s","")%>" readonly/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="Submit"/>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/all/products">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

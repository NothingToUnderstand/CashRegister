<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>Product info:</h2>

<table border="0">
    <tr>
        <td>ID :</td>
        <td>${product.getId()}</td>
    </tr><tr>
        <td>Name:</td>
        <td>${product.getName()}</td>
    </tr>
    <tr>
        <td>Quantity:</td>
        <td>${product.getQuantity()}</td>
    </tr>
    <tr>
        <td>Weight:</td>
        <td>${product.getWeight()}</td>
    </tr>
    <tr>
        <td>Price:</td>
        <td>${product.getPrice()}</td>
    </tr>
    <tr>
        <td colspan="3">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/all/products" >Back</a>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/product?id=${product.getId()}">Update</a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/product?id=${product.getId()}">Delete</a>
        </td>
    </tr>
</table>
</body>
</html>
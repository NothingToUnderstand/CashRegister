
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<h2>Products: </h2>
<a class="btn btn-success btn-md" href="${pageContext.request.contextPath}/create/product" >Create Product</a>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/" >Back</a>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Weight</th>
        <th>Price</th>
        <th>Info</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${products}" var="product" >
       <tr>
          <td>${product.getId()}</td>
          <td>${product.getName()}</td>
          <td>${product.getQuantity()}</td>
          <td>${product.getWeight()}</td>
          <td>${product.getPrice()}</td>
           <td> <a class="btn btn-info" href="${pageContext.request.contextPath}/info/product?id=${product.getId()}">Info</a></td>
           <td> <a class="btn btn-warning" href="${pageContext.request.contextPath}/update/product?id=${product.getId()}&name=${product.getName()}
           &quantity=${product.getQuantity()}&weight=${product.getWeight()}&price=${product.getPrice()}">Update</a></td>
           <td> <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/product?id=${product.getId()}">Delete</a></td>
       </tr>
    </c:forEach>
 </table>
</body>
</html>

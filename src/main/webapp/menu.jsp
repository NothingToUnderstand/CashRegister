<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<a href="${pageContext.request.contextPath}/all/products">All Products</a>
<a href="${pageContext.request.contextPath}/all/users">All Users</a>
<a href="${pageContext.request.contextPath}/login">Login</a>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<a href="${pageContext.request.contextPath}/create/user">Sign up</a>

&nbsp;
<span style="color:red">[${loginedUser.getId()},${loginedUser.getFullName()},${loginedUser.getRole()} ]</span>
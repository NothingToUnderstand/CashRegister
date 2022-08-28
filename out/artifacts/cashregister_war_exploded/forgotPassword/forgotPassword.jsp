<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="${sessionScope.lang}"/>
	<fmt:setBundle basename="messages"/>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
	rel='stylesheet'>
<link href='' rel='stylesheet'>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<style>
	<%@include file="../css/forgotPassword.css" %>
</style>
</head>
<header>
	<div>
		<jsp:include page="/menu.jsp"></jsp:include>
	</div>
</header>
<body oncontextmenu='return false' class='snippet-body'>
	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="forgot">
					<h2><fmt:message key="forgotpass"/></h2>
					<p><fmt:message key="changeyour"/></p>
					<ol class="list-unstyled">
						<li><span class="text-primary text-medium">1. </span><fmt:message key="enteryourfullname"/></li>
						<li><span class="text-primary text-medium">2. </span><fmt:message key="enterotp"/>
							</li>
						<li><span class="text-primary text-medium">3. </span><fmt:message key="newpass"/>
						</li>
					</ol>
				</div>
				<form class="card mt-4" action="forgotPassword" method="POST">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass"></span><fmt:message key="fullname"/></label> <input
								class="form-control" type="text" name="fullname" id="email-for-pass" required>
						</div>
					</div>
					<div class="card-footer">
						<button class="btn btn-success btn-block" type="submit"><fmt:message key="generateOtp"/></button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
	<script type='text/javascript' src=''></script>
	<script type='text/javascript' src=''></script>
	<script type='text/Javascript'></script>
</body>
</html>
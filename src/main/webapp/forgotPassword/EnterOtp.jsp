<html>
<head>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <style type="text/css">
        .form-gap {
            padding-top: 70px;
        }
    </style>
    <header>
        <div>
            <jsp:include page="/menu.jsp"></jsp:include>
        </div>
    </header>
</head>

<body>

<div class="form-gap"></div>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <h3>
                            <i class="fa fa-lock fa-4x"></i>
                        </h3>
                        <h2 class="text-center"><fmt:message key="enterotp"/></h2>

                        <div class="panel-body">

                            <form id="register-form" action="${pageContext.request.contextPath}/validateOtp" role="form"
                                  autocomplete="off"
                                  class="form" method="post">

                                <div class="form-group">
                                    <div class="input-group">
                                        <input id="opt" name="token"
                                               class="form-control" type="text" required="required"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input name="recover-submit"
                                           class="btn btn-lg btn-primary btn-block"
                                           value="<fmt:message key="resetpass"/>" type="submit">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
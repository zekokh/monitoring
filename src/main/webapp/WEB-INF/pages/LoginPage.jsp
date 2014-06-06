<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/signin.css"/>" rel="stylesheet"/>

    <title>Многопользовательская информационная система обмена геолокационными сообщениями</title>
</head>

<body>

<div class="container">

    <form class="form-signin" action="j_spring_security_check" method="POST">
        <h2 class="form-signin-heading">Войти в систему</h2>

        <input type="hidden" name="referer" value="${requestScope.referer}"/>
        <c:if test="${requestScope.error!=null}">
            <div class="alert">
                    ${requestScope.error}
            </div>
        </c:if>
        <c:if test="${fn:length(param)>0}">
            <div class="alert alert-danger">Не верный логин или пароль!</div>
        </c:if>
        <input type="email" class="form-control" placeholder="Email address" name="j_username" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="j_password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>

</div>
</body>
</html>

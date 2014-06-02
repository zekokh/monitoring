<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <input type="email"  class="form-control" placeholder="Email address" name="j_username" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="j_password" required>
        <c:if test="${requestScope.error!=null}">
            <div class="alert">
                    ${requestScope.error}
            </div>
        </c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>

</div> <!-- /container -->

<%--
<div class="container">
<div class="login-form">
    <h2 class="form-signin-heading" style="text-align: center;">Авторизация</h2>
    <form action="j_spring_security_check" method="POST">
        <input type="hidden" name="referer" value="${requestScope.referer}"/>
        <input type="text" class="form-control" placeholder="Логин" name="j_username"/>
        <input type="password" class="form-control" placeholder="Пароль" name="j_password"/>
        <c:if test="${requestScope.error!=null}">
            <div class="alert">
                    ${requestScope.error}
            </div>
        </c:if>
        <button class="btn primary" type="submit">Войти</button>
    </form>
</div>
</div>
--%>

<%--<div class="login-form">
    <h2>Авторизация</h2>

    <form action="j_spring_security_check" method="POST">
        <input type="hidden" name="referer" value="${requestScope.referer}"/>
        <fieldset>
            <div class="clearfix">
                <input type="text" placeholder="Логин" name="j_username"/>
            </div>
            <div class="clearfix">
                <input type="password" placeholder="Пароль" name="j_password"/>
            </div>

            <c:if test="${requestScope.error!=null}">
                <div class="alert">
                        ${requestScope.error}
                </div>
            </c:if>
            <c:if test="${fn:length(param)>0}">
                <div class="alert">Введенные логин и пароль не совпадают</div>
            </c:if>

            <button class="btn primary" type="submit">Войти</button>
        </fieldset>
    </form>
</div>--%>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>

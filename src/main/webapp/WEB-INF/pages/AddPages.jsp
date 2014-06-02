<%--
  Created by IntelliJ IDEA.
  User: ZR
  Date: 02.06.2014
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container">
    <h2 class="form-signin-heading" style="text-align: center;">Добавление пользователя</h2>
    <form class="form-signin" action="" method="POST">
        <input type="text" class="form-control" placeholder="Имя" name="first_name"/>
        <input type="text" class="form-control" placeholder="Фамилия" name="last_name"/>
        <input type="text" class="form-control" placeholder="mail" name="mail"/>
        <input type="text" class="form-control" placeholder="пароль" name="password"/>
        <c:if test="${requestScope.error!=null}">
            <div class="alert">
                ${requestScope.error}
            </div>
        </c:if>
        <button class="btn primary" type="submit">Войти</button>
    </form>
</div>
</body>
</html>

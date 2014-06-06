<%--
  Created by IntelliJ IDEA.
  User: ZR
  Date: 01.06.2014
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/sticky-footer-navbar.css"/>" rel="stylesheet"/>

    <title>Многопользовательская информационная система обмена геолокационными сообщениями</title>

</head>
<script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
<script type="text/javascript">
    ymaps.ready(init);
    var myMap,
            myPlacemark;

    function init(){
        myMap = new ymaps.Map("map", {
            center: [${loc.latitude}, ${loc.longitude}],
            zoom: 7
        });

        myPlacemark = new ymaps.Placemark([${loc.latitude}, ${loc.longitude}], {
            hintContent: '${loc.appleId}!',
            balloonContent: '${loc.appleId}'
        });

        myMap.geoObjects.add(myPlacemark);
    }
</script>
<body>
<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">GPS monitoring</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.firstName} ${user.lastName}  <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">${user.mail}</a></li>
                        <li><a href="#">Настройки</a></li>
                        <li><a href="/logout">Выйти</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<!-- Begin page content -->
<c:set var="userFlag" value="false"/>
<c:if test = "${not empty loc}">
    <c:set var="userFlag" value="true"/>
</c:if>
<div class="container">
    <div class="col-md-7">
        <div class="page-header">

            <c:choose>
                <c:when test="${userFlag==true}">
                </c:when>
                <c:when test="${userFlag==false}">
                    <div class="alert alert-warning">Нет данных для отображения</div>
                </c:when>
            </c:choose>

            <div id="map" style="width:600px;height:600px"></div>
        </div>
    </div>
    <div class="col-md-5">
        <c:choose>
            <c:when test="${userFlag==true}">
                <h4>Список устройств:</h4>
                <table class = "table">
                    <th>Apple id</th>
                    <th>Дата/время</th>
                    <tr><td>${loc.appleId}</td><td>${loc.date}</td></tr>
                </table>
            </c:when>
            <c:when test="${userFlag==false}">
                <br /><br /><br /><br /><br /><br /> <h4>Для передачи данных о местоположении объекта активируйте передатчик!</h4>
            </c:when>
        </c:choose>

    </div>
</div>

<div id="footer">
    <div class="container">
        <p class="text-muted">Многопользоватльская информационная система обмена геолокационными сообщениями.</p>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="components/header.jsp" %>

    <title>سامانه مدیریت کارکنان</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .carousel-inner img {
            width: 100%;
            height: 85%;
        }

        .carousel-caption {
            position: absolute;
            top: 33%;
            bottom: 50%;
            background-color: #343a40;
            color: white;
            font-size: large;
            opacity: 80%;
            border-radius: 15px;
        }
    </style>
</head>
<body style="background-color: black">

<div id="demo" class="carousel slide" data-ride="carousel">
<br/>

    <!-- Indicators -->
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
        <li data-target="#demo" data-slide-to="2"></li>
    </ul>

    <!-- The slideshow -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/images/slide02.jpg" alt="Dotin1" width="1100" height="500">
        </div>

        <div class="carousel-caption">
            <h3>به سامانه مدیریت کارکنان داتین خوش آمدید</h3>
            <br/>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/leaves/list">ورود به پنل مرخصی </a>
            <a class="btn btn-success" href="${pageContext.request.contextPath}/emails/list">ورود به پنل پیام ها</a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/employees/list">ورود به پنل کارکنان</a>

        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/slide01.jpg" alt="Dotin2" width="1100" height="500">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/slide04.jpg" alt="Dotin4" width="1100" height="500">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>
</body>
<%@include file="components/footer.jsp" %>
</html>
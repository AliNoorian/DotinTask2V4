<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" type=""></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" type=""></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>لیست کارمندان</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>
<%@include file="components/navigation.jsp" %>
<html var="listEmployees" value="/employees/list">
<%--<header style="background-color: #666;  padding: 30px;  text-align: center;  font-size: 35px;  color: white;">--%>
<%--    لیست کارمندان--%>
<%--</header>--%>
<div class="bodyContainer">

    <div class="container" dir="rtl">
        <%--        <h2 align="center">لیست کارمندان</h2><br>--%>
        <div class="table-responsive">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>نام</th>
                    <th>نام خانوادگی</th>
                    <th>وضعیت کاری</th>
                    <th>سمت</th>
                    <th>مدیر مربوطه</th>
                    <th>ایمیل</th>
                    <th>جنسیت</th>

                </tr>
                <c:url var="listEmployees" value="/employees/list"></c:url>
                <c:forEach var="Employee" items="${employees}">
                    <tr>
                        <td><c:out value="${Employee.id}"></c:out></td>
                        <td><c:out value="${Employee.firstName}"></c:out></td>
                        <td><c:out value="${Employee.lastName}"></c:out></td>
                        <td><c:out value="${Employee.active}"></c:out></td>
                        <td><c:out value="${Employee.employeeRole}"></c:out></td>
                        <td><c:out value="${Employee.manager}"></c:out></td>
                        <td><c:out value="${Employee.email}"></c:out></td>
                        <td><c:out value="${Employee.employeeGender}"></c:out></td>
                        <td><a href="/employees/showFormForUpdate/${Employee.id }" class="btn btn-info" role="button">ویرایش</a>
                        <td><a href="/employees/delete/${Employee.id}" class="btn btn-danger" role="button"
                               onclick="if (!(confirm('آیا از این کار اطمینان دارید؟'))) return false">حذف</a>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@include file="components/scripts.jsp" %>
</body>
</html>

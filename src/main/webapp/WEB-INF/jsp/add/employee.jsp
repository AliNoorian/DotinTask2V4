<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


    <title>کاربر جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>
<%--<header class="card-header" align="center">--%>
<%--    درج کاربر جدید--%>
<%--</header>--%>
<div class="container" align="center" dir="rtl">
    <h3 align="center">کاربر جدید</h3>
    <hr>

    <p class="h4 mb-4" align="right">درج کاربر جدید</p>

    <form:form method="POST" action="/employees/save" modelAttribute="employee">

        <form:input type="hidden" path="id"/>
        <br/>

        <div align="right">
            <form:label path="firstName">نام</form:label>
            <form:input type="text" path="firstName" class="form-control mb-4 col-4" id="inputFirstName"
                        placeholder="نام خود را وارد کنید"/>
        </div>

        <div align="right">

            <form:label path="lastName">نام خانوادگی</form:label>
            <form:input type="text" path="lastName" class="form-control mb-4 col-4" id="inputLastName"
                        placeholder="نام خانوادگی خود را وارد کنید"/>
        </div>

        <div align="right">
            <form:label path="active">وضعیت کاری</form:label><br/>
            <form:radiobutton cssClass="custom-radio" path="active" value="true"/>فعال<br/>
            <form:radiobutton cssClass="custom-radio" path="active" value="false"/>غیر فعال
        </div>
        <br/>

        <div align="right">
            <form:label path="email">ایمیل</form:label>
            <form:input type="email" path="email" class="form-control mb-4 col-4" id="inputEmail"
                        placeholder="ایمیل خود را وارد کنید"/>
        </div>

        <div align="right">
            <form:label path="employeeGender">جنسیت</form:label><br/>
            <form:radiobutton path="employeeGender" value="خانم"/> خانم<br/>
            <form:radiobutton path="employeeGender" value="آقا"/>آقا
        </div>
        <br/>

        <div align="right">
            <form:label path="employeeRole">سمت</form:label><br/>
            <form:select path="employeeRole">
                <c:forEach items="${categoryElements}" var="dep" varStatus="status">
                    <option value="${dep.id}">${dep.name}</option>
                </c:forEach>
            </form:select>
        </div>
        <br/>

        <div align="right">
            <form:label path="manager">مدیر</form:label>
            <form:select path="manager">
                <c:forEach items="${managers}" var="emp" varStatus="status">
                    <option value="${emp.id}">${emp.lastName}</option>
                </c:forEach>
            </form:select>
        </div>
        <br/>

        <div align="right">
            <a type="button" href="<%=request.getContextPath()%>/employees/list"
               class="btn btn-outline-danger">انصراف</a>
            <form:button type="submit" class="btn btn-outline-primary">ثبت</form:button>
        </div>

    </form:form>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" type=""></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" type=""></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>کاربر جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>
<%@include file="../components/navigation.jsp" %>
<header style="background-color: #666;  padding: 30px;  text-align: center;  font-size: 35px;  color: white;">
    درج کاربر جدید
</header>
<div class="centered cardContainer"
uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true" align="right">

    <form:form method="POST" action="/employees/save" modelAttribute="employee" align="right">

        <form:input type="hidden" path="id"/>

        <div>
            <form:label path="firstName">نام</form:label>
            <form:input type="text" path="firstName" class="form-control" id="inputFirstName"
                        placeholder="نام خود را وارد کنید"/>
        </div><br/>

        <div>

            <form:label path="lastName">نام خانوادگی</form:label>
            <form:input type="text" path="lastName" class="form-control" id="inputLastName"
                        placeholder="نام خانوادگی خود را وارد کنید"/>
        </div><br/>

        <div>
            <form:label path="active">وضعیت کاری</form:label><br/>
            <form:radiobutton path="active" value="true"/>فعال<br/>
            <form:radiobutton path="active" value="false"/>غیر فعال
        </div><br/>

        <div>
            <form:label path="email">ایمیل</form:label>
            <form:input type="email" path="email" class="form-control" id="inputEmail"
                        placeholder="ایمیل خود را وارد کنید"/>
        </div><br/>

        <div>
            <form:label path="employeeGender">جنسیت</form:label><br/>
            <form:radiobutton path="employeeGender" value="خانم"/> خانم<br/>
            <form:radiobutton path="employeeGender" value="آقا"/>آقا
        </div><br/>

        <div>
            <form:label path="employeeRole">سمت</form:label>
            <form:select path="employeeRole">
                <c:forEach items="${categoryElements}" var="dep" varStatus="status">
                    <option value="${dep.id}">${dep.persianTypeName}</option>
                </c:forEach>
            </form:select>
        </div><br/>

        <div>
            <form:label path="manager">مدیر</form:label>
            <form:select path="manager">
                <c:forEach items="${managers}" var="emp" varStatus="status">
                    <option value="${emp.id}">${emp.lastName}</option>
                </c:forEach>
            </form:select>
        </div><br/>

        <div>
            <a type="button" href="<%=request.getContextPath()%>/employees/list"
               class="btn btn-dark">انصراف</a>
        </div>

        <div>
            <form:button type="submit" class="btn btn-primary">ثبت</form:button>
        </div>

    </form:form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

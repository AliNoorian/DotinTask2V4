<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>نمایش اطلاعات کاربر</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">نمایش اطلاعات کاربر</h3>
    <hr>

    <form:form method="POST" action="/employees/save" modelAttribute="employee">

        <form:input type="hidden" path="id"/>
        <br/>

        <div align="right">
            <form:label path="firstName">نام</form:label>
            <form:input readonly="true" type="text" path="firstName" class="form-control mb-4 col-4" id="inputFirstName"/>
            <form:errors path="firstName" cssClass="error"/>

        </div>

        <div align="right">

            <form:label path="lastName">نام خانوادگی</form:label>
            <form:input readonly="true" type="text" path="lastName" class="form-control mb-4 col-4" id="inputLastName"/>
            <form:errors path="lastName" cssClass="error"/>

        </div>

        <div align="right">
            <form:label path="createDate">تاریخ ثبت نام</form:label><br/>
            <form:input readonly="true" class="form-control mb-4 col-4" path="createDate" value="${employee.createDate}" /><br/>
        </div>

        <div align="right">
        <form:label path="active">وضعیت کاری</form:label><br/>
        <form:input readonly="true" class="form-control mb-4 col-4" path="active" value="${employee.active?'فعال':'غیر فعال' }"/><br/>
        <form:errors path="active" cssClass="error"/>
    </div>

        <div align="right">
            <form:label path="email">ایمیل</form:label>
            <form:input readonly="true" type="email" path="email" class="form-control mb-4 col-4" id="inputEmail"/>
            <form:errors path="email" cssClass="error"/>

        </div>

        <div align="right">
            <form:label path="employeeGender">جنسیت</form:label><br/>
            <form:input readonly="true" path="employeeGender" class="form-control mb-4 col-4"/> <br/>
            <form:errors path="employeeGender" cssClass="error"/>

        </div>
        <div align="right">
            <form:label path="employeeRole.name">سمت</form:label><br/>
            <form:input readonly="true" class="form-control mb-4 col-4"  path="employeeRole.name"/>
        </div>

        <div align="right">
            <form:label path="manager.lastName">مدیر</form:label>
            <form:input readonly="true" path="manager.lastName" class="form-control mb-4 col-4"/>
        </div>
        <br/>

        <div align="right">
            <a type="button" href="/employees/showFormForUpdate/${id}" class="btn btn-outline-primary">ویرایش</a>
                <a type="button" href="<%=request.getContextPath()%>/employees/list"
                   class="btn btn-outline-danger">بازگشت</a>
        </div>

    </form:form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
<br/>
<br/>


<%@include file="../components/footer.jsp" %>

</html>

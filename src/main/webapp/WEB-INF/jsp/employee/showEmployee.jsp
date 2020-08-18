<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>کاربر جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">کاربر جدید</h3>
    <hr>

    <form:form method="POST" action="/employees/save" modelAttribute="employee">

        <form:input type="hidden" path="id"/>
        <br/>

        <div align="right">
            <form:label path="firstName">نام</form:label>
            <form:input type="text" path="firstName" class="form-control mb-4 col-4" id="inputFirstName"
                        placeholder="نام خود را وارد کنید"/>
            <form:errors path="firstName" cssClass="error" />

        </div>

        <div align="right">

            <form:label path="lastName">نام خانوادگی</form:label>
            <form:input type="text" path="lastName" class="form-control mb-4 col-4" id="inputLastName"
                        placeholder="نام خانوادگی خود را وارد کنید"/>
            <form:errors path="lastName" cssClass="error" />

        </div>

        <div align="right">
            <form:label path="active">وضعیت کاری</form:label><br/>
            <form:radiobutton cssClass="custom-radio" path="active" value="true"/>فعال<br/>
            <form:radiobutton cssClass="custom-radio" path="active" value="false"/>غیر فعال
            <form:errors path="active" cssClass="error" />

        </div>
        <br/>

        <div align="right">
            <form:label path="email">ایمیل</form:label>
            <form:input type="email" path="email" class="form-control mb-4 col-4" id="inputEmail"
                        placeholder="ایمیل خود را وارد کنید"/>
            <form:errors path="email" cssClass="error" />

        </div>

        <div align="right">
            <form:label path="employeeGender">جنسیت</form:label><br/>
            <form:radiobutton path="employeeGender" value="خانم"/> خانم<br/>
            <form:radiobutton path="employeeGender" value="آقا"/>آقا
            <form:errors path="employeeGender" cssClass="error" />

        </div>
        <br/>

        <div align="right">
            <form:label path="employeeRole">سمت</form:label><br/>
            <form:select path="employeeRole">
                <jsp:useBean id="categoryElements" scope="request" type="java.util.List"/>
                <c:forEach items="${categoryElements}" var="dep" varStatus="status">
                    <option value="${dep.id}">${dep.name}</option>
                </c:forEach>
            </form:select>
            <form:errors path="employeeRole" cssClass="error" />

        </div>
        <br/>

        <div align="right">
            <form:label path="manager">مدیر</form:label>
            <form:select path="manager">
                <jsp:useBean id="managers" scope="request" type="java.util.List"/>
                <c:forEach items="${managers}" var="emp" varStatus="status">
                    <option value="${emp.id}">${emp.lastName}</option>
                </c:forEach>
            </form:select>
            <form:errors path="manager" cssClass="error" />

        </div>
        <br/>

        <div align="right">
            <form:button type="submit" class="btn btn-outline-primary">ثبت کاربر</form:button>
            <a type="button" href="<%=request.getContextPath()%>/employees/list"
               class="btn btn-outline-danger">انصراف</a>
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

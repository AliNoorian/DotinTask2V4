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


    <title>درخواست مرخصی</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">درخواست مرخصی</h3>
    <hr>

    <p class="h4 mb-4" align="right">ثبت مرخصی جدید</p>

    <form:form method="POST" action="/leaves/save" modelAttribute="leave">

        <form:input type="hidden" path="id"/>


        <div align="right">
            <form:label path="leaveSubject">موضوع مرخصی</form:label>
            <form:input type="text" path="leaveSubject" class="form-control mb-4 col-4" id="inputLeaveSubject"
                        placeholder="موضوع مرخصی را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="leaveMessage">شرح مرخصی</form:label>
            <form:textarea type="message" path="leaveMessage" class="form-control mb-4 col-4" id="inputLeaveMessage"
                           placeholder="شرح مرخصی را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="leaveFrom">از تاریخ و ساعت</form:label>
            <form:input type="datetime-local" path="leaveFrom"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="leaveFrom">تا تاریخ و ساعت</form:label>
            <form:input type="datetime-local" path="leaveTo"/>
        </div>
        <br/>


        <div align="right">
            <form:button type="submit" class="btn btn-outline-primary"> ثبت مرخصی</form:button>
            <a type="button" href="<%=request.getContextPath()%>/leaves/list"
               class="btn btn-outline-danger">انصراف</a>
        </div>


    </form:form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

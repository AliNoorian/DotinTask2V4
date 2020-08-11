<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/images/logo.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>پیام جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">پیام جدید</h3>
    <hr>

    <p class="h4 mb-4" align="right">ثبت پیام جدید</p>

    <form:form method="POST" action="/email/save" modelAttribute="email">

        <form:input type="hidden" path="id"/>

        <div align="right">
            <form:label path="receivers">انتخاب گیرنده</form:label>
            <form:select type="text" path="receivers" class="form-control mb-4 col-4" id="selectEmailReceivers"
                         multiple="multiple"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="subject">موضوع پیام</form:label>
            <form:input type="text" path="subject" class="form-control mb-4 col-4" id="inputEmailSubject"
                        placeholder="موضوع پیام را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="message">شرح پیام</form:label>
            <form:textarea type="message" path="message" class="form-control mb-4 col-4" id="inputEmailMessage"
                           placeholder="شرح پیام را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <form:label for="file" path="attachment">ارسال فایل</form:label>
            <form:input name="file" path="attachment" type="file" class="form-control-file" id="file"
                        placeholder="ارسال فایل در صورت نیاز"/>
            <c:if test="${not empty email.attachment}">
                <c:url var="urlFile" value="/emails/upload">
                    <c:param name="emailId" value="${email.id}"/>
                </c:url>
                <a href="${urlFile}"><c:out value="attachmentFile"/></a>
            </c:if>
                <%--                    <form:button type="button" class="btn btn-primary" onclick="location.href='/emails/upload?emailId=${email.id}&file=file'">ارسال فایل</form:button>--%>

        </div>
        <br/>
        <br/>
        <br/>

        <div align="right">
            <form:button type="submit" class="btn btn-outline-primary">ارسال پیام</form:button>
            <a type="button" href="<%=request.getContextPath()%>/emails/list"
               class="btn btn-outline-danger">انصراف</a>
        </div>

    </form:form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

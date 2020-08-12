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

    <title>پیام جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>
<%@include file="../components/navigation.jsp" %>
<header style="background-color: #666;  padding: 30px;  text-align: center;  font-size: 35px;  color: white;">
    درج پیام جدید
</header>
<div class="centered cardContainer"
     uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true" align="right">

    <form:form method="POST" action="/email/save" modelAttribute="email">

        <form:input type="hidden" path="id"/>

        <div>
            <form:label path="receivers">انتخاب گیرنده</form:label>
            <form:select type="text" path="receivers" class="form-control" id="selectEmailReceivers"
                         multiple="multiple"/>
        </div>
        <br/>

        <div>
            <form:label path="subject">موضوع پیام</form:label>
            <form:input type="text" path="subject" class="form-control" id="inputEmailSubject"
                        placeholder="موضوع پیام را عنوان کنید"/>
        </div>
        <br/>

        <div>
            <form:label path="message">شرح پیام</form:label>
            <form:textarea type="message" path="message" class="form-control" id="inputEmailMessage"
                           placeholder="شرح پیام را عنوان کنید"/>
        </div>
        <br/>

        <div>
                <%--            <form:button type="file" class="btn btn-" id="uploadFile">انتخاب فایل</form:button>--%>
            <form:label for="file" path="attachment">ارسال فایل</form:label>
            <form:input name="file" path="attachment" type="file" class="form-control" id="file"
                        placeholder="ارسال فایل در صورت نیاز"/>
<%--                    <c:if test="${not empty email.attachment}">--%>
<%--                        <c:url var="urlFile" value="/emails/upload">--%>
<%--                            <c:param name="emailId" value="${email.id}"/>--%>
<%--                        </c:url>--%>
<%--                        <a href="${urlFile}"><c:out value="attachmentFile"/></a>--%>
<%--                    </c:if>--%>
<%--                    <form:button type="button" class="btn btn-primary" onclick="location.href='uploadFile?emailId=${email.id}'">ارسال فایل</form:button>--%>
                    <form:button type="button" class="btn btn-primary" onclick="location.href='/emails/upload?emailId=${email.id}&file=file'">ارسال فایل</form:button>

        </div>
        <br/>

        <div>
            <a type="button" href="<%=request.getContextPath()%>/emails/list"
               class="btn btn-dark">انصراف</a>
        </div>

        <div>
            <form:button type="submit" class="btn btn-primary">ارسال پیام</form:button>
        </div>

    </form:form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

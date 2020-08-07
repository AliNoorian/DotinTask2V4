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

    <title>لیست پیام ها</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>
<%@include file="components/navigation.jsp" %>
<html var="EmailsList" value="emails/list">

<div class="bodyContainer">

    <div class="container" dir="rtl">
        <div class="table-responsive">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>فرستنده</th>
                    <th>موضوع پیام</th>
                    <th colspan="2">وضعیت درخواست</th>

                </tr>
                <c:forEach var="email" items="${emails}">
                    <tr>
                        <td><c:out value="${email.id }"></c:out></td>
                        <td><c:out value="${email.sender }"></c:out></td>
                        <td><c:out value="${email.subject }"></c:out></td>
                        <td>
                            <c:choose>
                        <td>
                            <button class="btn btn-success"><c:out value=""></c:out>نمایش پیام</button>
                        </td>

                        <td>
                            <button class="btn btn-danger"><c:out value=""></c:out>حذف پیام</button>
                        </td>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@include file="components/scripts.jsp" %>
</body>
</html>

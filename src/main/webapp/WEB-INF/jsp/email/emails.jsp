<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../components/header.jsp" %>
    <title>لیست پیام ها</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>
<%@include file="../components/navigation.jsp" %>
<html var="listEmails" value="/emails/list">

<div class="bodyContainer">

    <div class="container" dir="rtl">
        <div class="table-responsive">
            <table class="table table-hover">
                <tr align="center">
                    <th>#</th>
                    <th>فرستنده</th>
                    <th>موضوع پیام</th>
                    <th colspan="2">وضعیت درخواست</th>

                </tr>
                <c:forEach var="email" items="${emails}">
                    <tr>
                        <td><c:out value="${email.id }"/></td>
                        <td><c:out value="${email.sender }"/></td>
                        <td><c:out value="${email.subject }"/></td>
                        <td>
<%--                            <c:choose>--%>
<%--                        <td>--%>
<%--                            <button class="btn btn-success"><c:out value="show"></c:out>نمایش پیام</button>--%>
<%--                        </td>--%>

<%--                        <td>--%>
<%--                            <button class="btn btn-danger"><c:out value="delete"></c:out>حذف پیام</button>--%>
<%--                        </td>--%>
<%--                        </c:choose>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<hr>

<footer class="card-footer" align="center" dir="rtl">
    <%
        int pages = (int) request.getAttribute("totalRecords") / 4;
        for (int i = 0; i <= pages; i++) {
    %>
    <a href="${pageContext.request.contextPath}/emails/list?page=<%=i%>"><%=i + 1 %>
    </a>
    <%} %>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/emails/list">

        برو به صفحه<label><br/>
        <input type="text" name="page"/>
    </label>&nbsp;
        <input type="submit" value="اعمال"/>
    </form>


</footer>
<%@include file="../components/scripts.jsp" %>
</body>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<%@include file="../components/footer.jsp" %>

</html>

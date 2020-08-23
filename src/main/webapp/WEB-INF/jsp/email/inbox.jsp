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
            <table class="table table-hover" align="center">
                <tr align="center">
                    <th align="center">#</th>
                    <th align="center">فرستنده</th>
                    <th align="center">موضوع پیام</th>
                    <th colspan="2" align="center">وضعیت درخواست</th>

                </tr>
                <c:forEach var="email" items="${ReceivedEmails}">
                    <tr>
                        <td align="center"><c:out value="${email.id }"/></td>
                        <td align="center"><c:out value="${email.sender.firstName} ${email.sender.lastName}"/></td>
                        <td align="center"><c:out value="${email.subject }"/></td>
                        <td align="center">
                            <a href="/emails/show/${email.id}" class="btn btn-primary" role="button" >نمایش پیام</a>
                          <a href="/emails/showFormForAdd" class="btn btn-success" role="button" >ارسال پاسخ</a>
                          <a href="/emails/delete/${email.id}" class="btn btn-danger" role="button"
                               onclick="if (!(confirm('آیا از این کار اطمینان دارید؟'))) return false">حذف پیام</a></td>
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

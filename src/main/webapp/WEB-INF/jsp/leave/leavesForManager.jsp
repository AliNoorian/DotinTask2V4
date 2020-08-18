<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@include file="../components/header.jsp" %>

    <title>لیست مرخصی ها</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>
<%@include file="../components/navigation.jsp" %>
<html var="LeavesList" value="leaves/list">

<div class="bodyContainer">

    <div class="container" dir="rtl">
        <div class="table-responsive">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>موضوع مرخصی</th>
                    <th>شرح مرخصی</th>
                    <th>از تاریخ و ساعت</th>
                    <th>تا تاریخ و ساعت</th>
                    <th>شناسه</th>
                    <th>نام</th>
                    <th>نام خانوادگی</th>
                    <th colspan="3">وضعیت درخواست</th>

                </tr>
                <jsp:useBean id="leaves" scope="request" type="java.util.List"/>
                <c:forEach var="leave" items="${leaves}">
                    <tr>
                        <td><c:out value="${leave.id }"/></td>
                        <td><c:out value="${leave.leaveSubject }"/></td>
                        <td><c:out value="${leave.leaveMessage }"/></td>
                        <td><c:out value="${leave.leaveFrom }"/></td>
                        <td><c:out value="${leave.leaveTo }"/></td>
                        <td><c:out value="${leave.employee.id }"/></td>
                        <td><c:out value="${leave.employee.firstName }"/></td>
                        <td><c:out value="${leave.employee.lastName }"/></td>
                        <td>
                            <button class="btn btn-success"><c:out value="Approved"/>تایید درخواست</button>
                        </td>

                        <td>
                            <button class="btn btn-danger"><c:out value="Rejected"/>رد درخواست</button>
                        </td>
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
    <a href="${pageContext.request.contextPath}/leaves/list?page=<%=i%>"><%=i + 1 %>
    </a>
    <%} %>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/leaves/list">

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

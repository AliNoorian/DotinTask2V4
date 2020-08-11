<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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

    <title>لیست مرخصی ها</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>
<%@include file="components/navigation.jsp" %>
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
                <c:forEach var="leave" items="${leaves}">
                    <tr>
                        <td><c:out value="${leave.id }"></c:out></td>
                        <td><c:out value="${leave.leaveSubject }"></c:out></td>
                        <td><c:out value="${leave.leaveMessage }"></c:out></td>
                        <td><c:out value="${leave.leaveFrom }"></c:out></td>
                        <td><c:out value="${leave.leaveTo }"></c:out></td>
                        <td><c:out value="${leave.employee.id }"></c:out></td>
                        <td><c:out value="${leave.employee.firstName }"></c:out></td>
                        <td><c:out value="${leave.employee.lastName }"></c:out></td>
                        <td>
                            <c:choose>
                            <c:when test="${leave.leaveStatus eq 0 }">
                        <td>
                            <button class="btn btn-warning"><c:out value="Pending"></c:out>تعلیق درخواست</button>
                        </td>
                        </c:when>
                        <c:when test="${leave.leaveStatus eq 1 }">
                            <td>
                                <button class="btn btn-success"><c:out value="Approved"></c:out>تایید درخواست</button>
                            </td>
                        </c:when>
                        <c:when test="${leave.leaveStatus eq 2}">
                            <td>
                                <button class="btn btn-danger"><c:out value="Rejected"></c:out>رد درخواست</button>
                            </td>
                        </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
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

<%@include file="components/scripts.jsp" %>
</body>
</html>

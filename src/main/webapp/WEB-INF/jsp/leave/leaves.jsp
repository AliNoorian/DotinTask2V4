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

<div class="bodyContainer" align="center">
    <hr>
    <c:if test="${message != null }">
        <div class="alert alert-danger">
            <strong align="center"><c:out value=" ${message} "/></strong>
        </div>
    </c:if>
    <hr>
    <div class="container" dir="rtl">

        <div class="table-responsive">
            <table class="table table-hover">
                <tr align="center">
                    <th>#</th>
                    <th>موضوع مرخصی</th>
                    <th>از تاریخ و ساعت</th>
                    <th>تا تاریخ و ساعت</th>
                    <th>وضعیت مرخصی</th>
                    <th colspan="2">وضعیت درخواست</th>

                </tr>
                <c:forEach var="leave" items="${leaves}">
                    <tr align="center">
                        <td><c:out value="${leave.id }"/></td>
                        <td><c:out value="${leave.leaveSubject }"/></td>
                        <td dir="ltr"><c:out value="${leave.leaveFrom }"/></td>
                        <td dir="ltr"><c:out value="${leave.leaveTo }"/></td>
                        <td><c:out value="${leave.leaveStatus.name}"/></td>


                        <td><a href="/leaves/show/${leave.id }" class="btn btn-info" role="button">نمایش جزئیات</a>

                            <a href="/leaves/setApproved/${leave.id }" class="btn btn-success" role="button">موافقت</a>

                        <a href="/leaves/setRejected/${leave.id }" class="btn btn-danger"
                           onclick="if (!(confirm('آیا از این کار اطمینان دارید؟'))) return false" role="button">رد
                            درخواست</a></td>

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

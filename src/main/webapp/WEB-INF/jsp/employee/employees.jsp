<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@include file="../components/header.jsp" %>

    <title>لیست کارمندان</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>
<%@include file="../components/navigation.jsp" %>
<html var="listEmployees" value="/employees/list">
<%--<header style="background-color: #666;  padding: 30px;  text-align: center;  font-size: 35px;  color: white;">--%>
<%--    لیست کارمندان--%>
<%--</header>--%>
<div class="bodyContainer">

    <div class="container" dir="rtl">
        <%--        <h2 align="center">لیست کارمندان</h2><br>--%>
        <div class="table-responsive">
            <table class="table table-hover">
                <tr align="center">
                    <th>#</th>
                    <th>نام</th>
                    <th>نام خانوادگی</th>
                    <th>وضعیت کاری</th>
                    <th>سمت</th>
                    <th>مدیر مربوطه</th>
                    <th>ایمیل</th>
                    <th>جنسیت</th>
                    <th colspan="2">وضعیت درخواست</th>

                </tr>
                <c:forEach var="Employee" items="${employees}">
                    <tr align="center">
                        <td><c:out value="${Employee.id}"/></td>
                        <td><c:out value="${Employee.firstName}"/></td>
                        <td><c:out value="${Employee.lastName}"/></td>
                        <td><c:out value="${Employee.active?'فعال':'غیر فعال' }"/></td>
                        <td><c:out value="${Employee.employeeRole.name}"/></td>
                        <td><c:out value="${Employee.manager}"/></td>
                        <td><c:out value="${Employee.email}"/></td>
                        <td><c:out value="${Employee.employeeGender}"/></td>
                        <td><a href="/employees/showFormForUpdate/${Employee.id }" class="btn btn-info" role="button" >ویرایش</a>
                        <a href="/employees/delete/${Employee.id}" class="btn btn-danger" role="button"
                               onclick="if (!(confirm('آیا از این کار اطمینان دارید؟'))) return false">حذف</a></td>
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

</body>
<div class="card-footer" align="center" dir="rtl">
    <%
        int pages = (int) request.getAttribute("totalRecords") / 4;
        for (int i = 0; i <= pages; i++) {
    %>
    <a href="${pageContext.request.contextPath}/employees/list?page=<%=i%>"><%=i + 1 %>
    </a>
    <%} %>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/employees/list">

        برو به صفحه<label><br/>
        <input type="text" name="page"/>
    </label>&nbsp;
        <input type="submit" value="اعمال"/>
    </form>


</div>
<%@include file="../components/scripts.jsp" %>
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

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>کاربر جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">کاربر جدید</h3>
    <hr>
    <hr>
    <c:if test="${message != null }">
    <div class="alert alert-danger">
        <strong><c:out value=" ${message} "/></strong>
    </div>
    </c:if>
    <hr>
    <div class="table-responsive" align="center">

        <form method="POST" action="${pageContext.request.contextPath}/employees/save" path="employee">

            <table class="table-responsive">
                <tr align="center">
                    <td>نام</td>
                    <td>
                        <label>
                            <input type="text" path="firstName" name="firstName"/>
                        </label>
                    </td>
                </tr>
                <tr align="center">
                    <td>نام خانوادگی</td>
                    <td>
                        <label>
                            <input type="text" path="lastName" name="lastName"/>
                        </label>
                    </td>
                </tr>
                <tr align="center">
                    <td>وضعیت کاری</td>
                    <td><input type="radio" id="true" name="active" path="active" value="true">
                        <label for="true">فعال</label><br></td>
                    <td><input type="radio" id="false" name="active" path="active" value="false">
                        <label for="false">غیر فعال</label><br>
                    </td>
                </tr>
                <tr align="center">
                    <td>ایمیل</td>
                    <td>
                        <label>
                            <input type="text" name="email" path="email"/>
                        </label>
                    </td>
                </tr>
                <tr align="center">
                    <td>جنسیت</td>
                    <td><input type="radio" id="خانم" path="employeeGender" name="employeeGender" value="خانم">
                        <label for="خانم">خانم</label><br></td>
                    <td><input type="radio" id="آقا" path="employeeGender" name="employeeGender" value="آقا">
                        <label for="آقا">آقا</label><br>
                    </td>
                </tr>
                <tr align="center">
                    <td>سمت</td>
                    <td>
                        <label for="employeeRole"></label>
                        <select name="role" id="employeeRole" path="employeeRole" class="browser-default custom-select">
                            <c:forEach items="${categoryElements}" var="role">
                                <c:choose>
                                    <c:when test="${employee.employeeRole.name != null }">
                                        <option selected="${employee.employeeRole.id}"
                                                value="${employee.employeeRole.id}">
                                                ${role.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${role.id}">${role.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr align="center">
                    <td>مدیر</td>
                    <td>
                        <label for="manager"></label>
                        <select name="manager" id="manager" path="manager" class="browser-default custom-select">
                            <c:forEach items="${managers}" var="emp">
                                <c:choose>
                                    <c:when test="${employee.manager.lastName != null }">
                                        <option selected="${employee.manager.id}"
                                                value="${employee.manager.id}">${emp.firstName} ${emp.lastName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${emp.id}">${emp.firstName} ${emp.lastName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <div align="center">
                <button type="submit" class="btn btn-outline-primary">ثبت کاربر</button>
                <a type="button" href="<%=request.getContextPath()%>/employees/list"
                   class="btn btn-outline-danger">انصراف</a>
            </div>
        </form>

    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
    <%@include file="../components/scripts.jsp" %>
</body>
<br/>
<br/>


<%@include file="../components/footer.jsp" %>

</html>

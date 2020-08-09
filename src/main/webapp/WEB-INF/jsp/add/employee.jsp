<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Registration Page</title>
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <style type="text/css">

        button {
            padding: 10px;
        }

        form {
            display: table;
        }
        form div {
            display: table-row;
        }
        label, input, span, select {
            display: table-cell;
            margin: 5px;
            text-align: left;
        }
        input[type=text], input[type=password], select, textarea {
            width: 200px;
            margin: 5px;
        }

        form div div {
            display: table-cell;
        }
    </style>


    <title>کاربر جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>
<header class="card-header" align="center">
    درج کاربر جدید
</header>
<div class="page-wrapper bg-blue p-t-100 p-b-100 font-robo" >
    <div class="wrapper wrapper--w680">
        <div class="card card-1">
            <div class="card-heading"></div>
            <div class="card-body">
                <form:form method="POST" action="/employees/save" modelAttribute="employee" >

                    <form:input type="hidden" path="id"/>
                    <br/>
                    <br/>
                    <br/>
                    <div>
                        <form:label path="firstName">نام</form:label>
                        <form:input type="text" path="firstName" class="form-control" id="inputFirstName"
                                    placeholder="نام خود را وارد کنید"/>
                    </div>
                    <br/>

                    <div>

                        <form:label path="lastName">نام خانوادگی</form:label>
                        <form:input type="text" path="lastName" class="form-control" id="inputLastName"
                                    placeholder="نام خانوادگی خود را وارد کنید"/>
                    </div>
                    <br/>

                    <div align="center">
                        <form:label path="active"  cssStyle="align-content: center" >وضعیت کاری</form:label><br/>
                        <form:radiobutton path="active" value="true"/>فعال<br/>
                        <form:radiobutton path="active" value="false"/>غیر فعال
                    </div>
                    <br/>

                    <div>
                        <form:label path="email">ایمیل</form:label>
                        <form:input type="email" path="email" class="form-control" id="inputEmail"
                                    placeholder="ایمیل خود را وارد کنید"/>
                    </div>
                    <br/>

                    <div align="center">
                        <form:label path="employeeGender"  cssStyle="align-content: center">جنسیت</form:label><br/>
                        <form:radiobutton path="employeeGender" value="خانم"/> خانم<br/>
                        <form:radiobutton path="employeeGender" value="آقا"/>آقا
                    </div>
                    <br/>

                    <div>
                        <form:label path="employeeRole" cssStyle="align-content: center">سمت</form:label><br/>
                        <form:select path="employeeRole">
                            <c:forEach items="${categoryElements}" var="dep" varStatus="status">
                                <option value="${dep.id}">${dep.name}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <br/>

                    <div>
                        <form:label path="manager"  cssStyle="align-content: center">مدیر</form:label>
                        <form:select path="manager">
                            <c:forEach items="${managers}" var="emp" varStatus="status">
                                <option value="${emp.id}">${emp.lastName}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <br/>

                    <div>
                        <a type="button" href="<%=request.getContextPath()%>/employees/list"
                           class="btn btn-dark">انصراف</a>
                    </div>

                    <div>
                        <form:button type="submit" class="btn btn-primary">ثبت</form:button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

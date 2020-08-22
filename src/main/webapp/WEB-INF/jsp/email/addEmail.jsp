<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>پیام جدید</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">پیام جدید</h3>
    <hr>

    <p class="h4 mb-4" align="right">ثبت پیام جدید</p>

    <form:form method="POST" action="/emails/send" modelAttribute="email" class="form form-group">




<%--    <div align="right">--%>
<%--        <form:label path="sender">فرستنده</form:label>--%>

<%--        <form:select path="sender">--%>
<%--            <c:forEach items="${employeeReceivers}" var="emp">--%>
<%--                <c:choose>--%>

<%--                        <option value="${emp.id}">${emp.firstName} ${emp.lastName}</option>--%>
<%--                </c:choose>--%>
<%--            </c:forEach>--%>
<%--        </form:select>--%>

<%--    </div>--%>



        <form:input type="hidden" path="id"/>

    <div align="right">
                        <form:label path="receivers" id="receivers" for="chooseReceivers">انتخاب گیرنده</form:label>
<%--        <form:select multiple="true" class="form-control mb-4 col-4"--%>
<%--                     path="receivers" items="${employeeReceivers}" itemValue="lastName"/>--%>


        <form:select multiple="true" path="receivers" id="chooseReceivers"
                                     class="form-control mb-4 col-4" size="${employeeReceivers.size()}">
                            <c:forEach items="${employeeReceivers}" var="receiver" varStatus="status">
                                <option multiple="true" value="${receiver.id}">${receiver.firstName} ${receiver.lastName}</option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="receivers" cssStyle="color: red"/>
    </div>

<%--        <input type="button" onclick="addReceiver()" class="btn btn-outline-success" value="افزودن گیرنده جدید"/>--%>
<%--        <label>--%>
<%--            <select path="receivers" id="selected">--%>
<%--                <c:forEach items="${employeeReceivers}" var="receiver">--%>
<%--                    <option value="${receiver.id}">${receiver.firstName} ${receiver.lastName}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </label>--%>
<%--        <table>--%>
<%--            <div type="input" multiple="multiple" id="selectedReceivers">--%>
<%--        </table>--%>
<%--        <br/>--%>

        <div align="right">
            <form:label path="subject">موضوع پیام</form:label>
            <form:input type="text" path="subject" class="form-control mb-4 col-4" id="inputEmailSubject"
                        placeholder="موضوع پیام را عنوان کنید"/>
            <form:errors path="subject" cssStyle="color: red"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="message">شرح پیام</form:label>
            <form:textarea type="message" path="message" class="form-control mb-4 col-4" id="inputEmailMessage" rows="5"
                           cols="20"
                           placeholder="شرح پیام را عنوان کنید"/>
            <form:errors path="message" cssStyle="color: red"/>

        </div>
        <br/>

        <div align="right">
            <form:label  for="file" path="attachment">ارسال فایل</form:label>
            <form:input modelAttribute="file" name="file" path="attachment" type="file" class="form-control mb-4 col-4" id="file"
                        placeholder="ارسال فایل در صورت نیاز"/>


        </div>
        <br/>
        <br/>
        <br/>

        <div align="right">
            <form:button type="submit" class="btn btn-outline-primary">ارسال پیام</form:button>
            <a type="button" href="<%=request.getContextPath()%>/emails/list"
               class="btn btn-outline-danger">انصراف</a>
        </div>

        </form:form>
    </div>


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
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


<%--<script type="text/javascript">--%>
<%--    let i;--%>

<%--    function addReceiver() {--%>
<%--        document.getElementById("selectedReceivers").innerHTML += addEmployeeReceiver();--%>
<%--        return null;--%>
<%--    }--%>

<%--    function addEmployeeReceiver() {--%>

<%--        i++;--%>
<%--        return '<div id=receivers' + i + '>' +--%>
<%--            '                <c:forEach items="${employeeReceivers}" var="receiver">\n' +--%>
<%--            '                    <option value="${receiver.id}">${receiver.firstName} ${receiver.lastName}</option>\n' +--%>
<%--            '                </c:forEach>' +--%>
<%--            '<a cssStyle="color: red"\n' +--%>
<%--            '                    title="حذف این کاربر" onclick="remove(' + i + ')"> x </a>   ' +--%>
<%--            '           </div>';--%>
<%--    }--%>

<%--    function remove(receiverId) {--%>
<%--        document.getElementById("receivers" + receiverId).remove();--%>
<%--        i--;--%>
<%--    }--%>
<%--</script>--%>


<%@include file="../components/footer.jsp" %>

</html>

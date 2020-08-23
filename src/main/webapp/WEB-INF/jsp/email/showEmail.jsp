<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>نمایش اطلاعات پیام</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>

<div class="container" align="center" dir="rtl">
    <h3 align="center">نمایش پیام</h3>
    <hr>

    <form:form method="POST" action="/emails/save" modelAttribute="email">

        <form:input type="hidden" path="id"/>
        <br/>

<%--        <div align="right">--%>
<%--            <form:label path="sender">فرستنده</form:label>--%>
<%--            <form:input readonly="true" type="text" path="sender.lastName" class="form-control mb-4 col-4" id="inputFirstName">--%>
<%--            </form:input>--%>
<%--            <form:errors path="sender" cssClass="error"/>--%>

<%--        </div>--%>

<%--        <div align="right">--%>

<%--            <form:label path="receivers">گیرنده</form:label>--%>
<%--            <form:select readonly="true"  path="receivers" id="" cssClass="browser-default custom-select" multiple="multiple"--%>
<%--                         size="${email.receivers.size()}">--%>
<%--                <form:options readonly="true" items="${email.receivers}" itemValue="id" itemLabel="lastName"/>--%>
<%--            </form:select>--%>
<%--            <form:errors path="receivers" cssClass="error"/>--%>
<%--        </div>--%>

        <div align="right">
            <form:label path="subject">موضوع پیام</form:label>
            <form:input readonly="true" type="text" path="subject" class="form-control mb-4 col-4" id="inputEmailSubject"
                        placeholder="موضوع پیام را عنوان کنید"/>
            <form:errors path="subject" cssStyle="color: red"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="message">شرح پیام</form:label>
            <form:textarea readonly="true" type="message" path="message" class="form-control mb-4 col-4" id="inputEmailMessage" rows="5"
                           cols="20"
                           placeholder="شرح پیام را عنوان کنید"/>
            <form:errors path="message" cssStyle="color: red"/>

<%--            <c:choose>--%>
<%--                <c:when test="${emailContent==null}">--%>
<%--                </br>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    </div>--%>
<%--                    <div align="right">--%>
<%--                        <a href="${emailContent}" class="btn btn-primary"> دانلود فایل همراه پیام</a>--%>
<%--                    </div>                </c:otherwise>--%>
<%--            </c:choose>--%>

        <br/>
        <br/>

<%--        <table>--%>
<%--            <c:forEach items="${email.attachment}" var="file">--%>
<%--                <tr>--%>
<%--                    <td><img src="data:image/jpg;base64,${file}"/></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>

<%--        <div align="right">--%>
<%--            <form:label  for="file" path="attachment">ارسال فایل</form:label>--%>
<%--            <form:input readonly="true" modelAttribute="file" name="file" path="attachment" type="file" class="form-control mb-4 col-4" id="file"--%>
<%--                        placeholder="ارسال فایل در صورت نیاز"/>--%>

<%--        </div>--%>



        <div align="right">
            <a href="${pageContext.request.contextPath}/emails/showFormForAdd" class="btn btn-outline-success" role="button" >ارسال پاسخ</a>
            <a type="button" href="<%=request.getContextPath()%>/employees/list"
               class="btn btn-outline-danger">بازگشت</a>
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
<br/>
<br/>
<br/>


<%@include file="../components/footer.jsp" %>

</html>

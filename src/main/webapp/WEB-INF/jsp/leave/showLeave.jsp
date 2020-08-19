<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>نمایش مرخصی</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>


<div class="container" align="center" dir="rtl">
    <h3 align="center">نمایش مرخصی</h3>
    <hr>
    <c:if test="${message != null }">
    <div class="alert alert-danger">
        <strong><c:out value=" ${message} "/>!</strong>
    </div>
    </c:if>
    <hr>

    <p class="h4 mb-4" align="right">نمایش جزئیات مرخصی</p>

    <form:form method="POST" action="/leaves/save" modelAttribute="leave">
        <form:input type="hidden" path="id"/>


        <div align="right">
            <form:label path="leaveSubject">موضوع مرخصی</form:label>
            <form:input type="text" path="leaveSubject" readonly="true"
                        class="form-control mb-4 col-4" id="inputLeaveSubject"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="leaveMessage">شرح مرخصی</form:label>
            <form:textarea type="message" readonly="true" path="leaveMessage"
                           class="form-control mb-4 col-4" id="inputLeaveMessage"/>
        </div>
        <br/>

        <div align="right">
            <div class="input-group">

                <form:label path="leaveFrom" cssStyle="margin-left: 1px">از تاریخ و ساعت </form:label>

                <form:input type="text" readonly="true" id="inputDate1"
                            path="leaveFrom"
                            dir="ltr"/>
            </div>
        </div>

        <br/>

        <div align="right">
            <div class="input-group">

                <form:label path="leaveFrom" cssStyle="margin-left: 4px">تا تاریخ و ساعت </form:label>
                <form:input type="text" readonly="true" id="inputDate2"
                            path="leaveTo" dir="ltr"/>


            </div>
        </div>
        <br/>


        <div align="right">
            <a href="/leaves/setApproved/${id}" class="btn btn-outline-success" role="button">موافقت</a>

            <a href="/leaves/setRejected/${id}" class="btn btn-outline-danger"
               onclick="if (!(confirm('آیا از این کار اطمینان دارید؟'))) return false" role="button">رد
                درخواست</a>
            <a href="${pageContext.request.contextPath}/leaves/list" class="btn btn-outline-secondary" role="button">بازگشت</a>

        </div>

    </form:form>


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
<%@include file="../components/scripts.jsp" %>
<%@include file="../components/footer.jsp" %>

</html>

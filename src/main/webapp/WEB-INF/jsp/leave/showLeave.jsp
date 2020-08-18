<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../components/header.jsp" %>

    <title>درخواست مرخصی</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body dir="rtl" align="center">
<%@include file="../components/navigation.jsp" %>


<div class="container" align="center" dir="rtl">
    <h3 align="center">درخواست مرخصی</h3>
    <hr>


    <p class="h4 mb-4" align="right">ثبت مرخصی جدید</p>

    <form:form method="POST" action="/leaves/save" modelAttribute="leave">

        <form:input type="hidden" path="id"/>


        <div align="right">
            <form:label path="leaveSubject">موضوع مرخصی</form:label>
            <form:input type="text" path="leaveSubject" class="form-control mb-4 col-4" id="inputLeaveSubject"
                        placeholder="موضوع مرخصی را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <form:label path="leaveMessage">شرح مرخصی</form:label>
            <form:textarea type="message" path="leaveMessage" class="form-control mb-4 col-4" id="inputLeaveMessage"
                           placeholder="شرح مرخصی را عنوان کنید"/>
        </div>
        <br/>

        <div align="right">
            <div class="input-group">

                <form:label path="leaveFrom" cssStyle="margin-left: 1px">از تاریخ و ساعت </form:label>

                <form:input type="text" id="inputDate1" placeholder=" ⇐ انتخاب تاریخ به همراه ساعت"
                            path="leaveFrom"
                            dir="ltr"/>
                <span class="input-group-text cursor-pointer" id="input1">
                <img src="${pageContext.request.contextPath}/images/date2.png" width="15" height="15" alt="a">
            </span>
                <input type="hidden" id="date_input1" value=""/><br/>

            </div>
        </div>

        <br/>

        <div align="right">
            <div class="input-group">

                <form:label path="leaveFrom" cssStyle="margin-left: 4px">تا تاریخ و ساعت </form:label>
                <form:input type="text" id="inputDate2" placeholder=" ⇐ انتخاب تاریخ به همراه ساعت"
                            path="leaveTo" dir="ltr"/>

                <span class="input-group-text cursor-pointer" id="input2">
                    <img src="${pageContext.request.contextPath}/images/date2.png" width="15" height="15" alt="a">
                </span>
                <input type="hidden" id="date_input2" value=""/><br/>


            </div>
        </div>
        <br/>


        <div align="right">
            <form:button type="submit" class="btn btn-outline-primary"> ثبت مرخصی</form:button>
            <a type="button" href="<%=request.getContextPath()%>/leaves/list"
               class="btn btn-outline-danger">انصراف</a>
        </div>


    </form:form>
</div>
<%@include file="../components/scripts.jsp" %>
<script type="text/javascript">
    $('#input1').MdPersianDateTimePicker({
        targetTextSelector: '#inputDate1',
        fromDate: true,
        groupId: 'rangeSelector1',
        enableTimePicker: true,
        dateFormat: 'yyyy-MM-dd HH:mm',
        textFormat: 'yyyy-MM-dd HH:mm از ساعت',
    });
    $('#input2').MdPersianDateTimePicker({
        targetTextSelector: '#inputDate2',
        toDate: true,
        groupId: 'rangeSelector1',
        placement: 'top',
        enableTimePicker: true,
        dateFormat: 'yyyy-MM-dd HH:mm',
        textFormat: 'yyyy-MM-dd HH:mm تا ساعت',
    });
</script>

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

<%@include file="../components/footer.jsp" %>

</html>

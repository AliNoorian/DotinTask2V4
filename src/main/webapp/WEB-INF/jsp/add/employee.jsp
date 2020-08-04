<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../components/stylesheets.jsp" %>
</head>
<body>

<div class="bodyContainer">
    <%@include file="../components/navigation.jsp" %>
    <main class="main">
        <div class="mainBody">
            <div class="formContainer">
                <%--@elvariable id="employee" type="com.dotin.dotintasktwo.model.Employee"--%>
                <form:form modelAttribute="employee" action="employee" method="post" name="employee"
                           id="employee">
                    <fieldset class="uk-fieldset">
                        <legend class="uk-legend">ایجاد کارمند جدید</legend>
                        <div class="uk-margin">
                            <label>
                                <input id="firstName" name="firstName" class="uk-input uk-form-wiukh-large" type="text"
                                       placeholder="نام">
                            </label>
                        </div>
                        <div class="uk-margin">
                            <label>
                                <input id="lastName" name="lastName" class="uk-input uk-form-wiukh-large" type="text"
                                       placeholder="نام خانوادگی">
                            </label>
                        </div>
                        <div class="uk-margin">
                            <label>
                                <input id="employeeEmail" name="employeeEmail" class="uk-input uk-form-wiukh-large"
                                       type="email" placeholder="ایمیل">
                            </label>
                        </div>
                        <div class="uk-margin uk-grid-small uk-child-wiukh-auto uk-grid">
                            <label><input id="programmerManagerSelect" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="manager" value="مدیر برنامه نویس">&nbsp;مدیر برنامه نویس</label>
                            <label><input id="testerManagerSelect" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="manager" value="مدیر تست">&nbsp;مدیر تست</label>
                        </div>
                        <div class="uk-margin uk-grid-small uk-child-wiukh-auto uk-grid">
                            <label><input id="programmer" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeRole" value="یرنامه نویس">&nbsp;برنامه نویس</label>
                            <label><input id="tester" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeRole" value="تستر">&nbsp;تستر</label>
                            <label><input id="programmerManager" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeRole" value="مدیر برنامه نویس">&nbsp;مدیر برنامه نویس</label>
                            <label><input id="testerManager" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeRole" value="مدیر تست">&nbsp;مدیر تست</label>
                        </div>

                        <div class="uk-margin uk-grid-small uk-child-wiukh-auto uk-grid">
                            <label><input id="employeeGenderMale" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeGender" value="آقا">&nbsp;آقا</label>
                            <label><input id="employeeGenderFemale" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="employeeGender" value="خانم">&nbsp;خانم</label>
                        </div>


                        <div class="uk-margin">
                            <button id="submitButton" type="button" class="uk-button uk-button-primary">ثبت</button>
                            &nbsp;&nbsp;
                            <span id="checkIcon" class="uk-margin-small-right" uk-icon="check"
                                  style="color: mediumspringgreen; display: none;"></span>

                        </div>
                        <div>
                            <c:if test="submittedForm">
                                <jsp:useBean id="status"/>

                                <h6>${status ? "Successfully added " : "Failed to add "}a employee!</h6>
                            </c:if>
                        </div>
                    </fieldset>
                </form:form>
            </div>

        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/employee.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

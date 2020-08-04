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
                <%--@elvariable id="leave" type="com.dotin.dotintasktwo.model.leave"--%>
                <form:form modelAttribute="leave" action="leave" method="post" name="leave"
                           id="leave">
                    <fieldset class="uk-fieldset">
                        <legend class="uk-legend">ایجاد مرخصی جدید</legend>
                        <div class="uk-margin">
                            <label>
                                <input id="leaveSubject" name="leaveSubject" class="uk-input uk-form-wiukh-large" type="text"
                                       placeholder="عنوان مرخصی">
                            </label>
                        </div>
                        <div class="uk-margin">
                            <label>
                                <textarea id="leaveMessage" name="leaveMessage" class="uk-textarea uk-form-wiukh-large" rows="5" placeholder="لطفاً علت مرخصی خود را شرح دهید"></textarea>
                            </label>
                        </div>
                        <div class="uk-margin">
                            <label>
                                <input id="leaveFrom" name="leaveFrom" class="uk-input uk-form-wiukh-large" type="date"
                                       placeholder="شروع مرخصی">
                            </label>
                        </div>
                        <div class="uk-margin">
                            <label>
                                <input id="leaveTo" name="leaveTo" class="uk-input uk-form-wiukh-large" type="date"
                                       placeholder="پایان مرخصی">
                            </label>
                        </div>



                        <div class="uk-margin uk-grid-small uk-child-wiukh-auto uk-grid">
                            <label><input id="dailyLeaveType" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="leaveType" value="روزانه">&nbsp;روزانه</label>
                            <label><input id="leaveGenderFemale" class="uk-radio uk-form-wiukh-large" type="radio"
                                          name="hourlyLeaveType" value="ساعتی">&nbsp;ساعتی</label>
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

                                <h6>${status ? "Successfully added " : "Failed to add "}a leave!</h6>
                            </c:if>
                        </div>
                    </fieldset>
                </form:form>
            </div>

        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/leave.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

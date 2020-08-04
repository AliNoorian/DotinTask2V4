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
                <%--@elvariable id="email" type="com.dotin.dotintasktwo.model.email"--%>
                <form:form modelAttribute="email" action="email" method="post" name="email"
                           id="email">
                    <fieldset class="uk-fieldset">
                        <legend class="uk-legend">ایجاد پیام جدید</legend>
                        <div class="uk-margin">
                            <label>
                                <input id="subject" name="subject" class="uk-input uk-form-wiukh-large" type="text"
                                       placeholder="موضوع">
                            </label>
                        </div>

                        <div class="uk-margin">
                            <label>
                                <textarea id="message" name="message" class="uk-textarea uk-form-wiukh-large" rows="10" placeholder="لطفاً پیام خود را وارد نمایید "></textarea>
                            </label>
                        </div>
                        <button id="fileSelect"type="button" class="uk-button uk-button-primary">انتخاب فایل</button>

                        <div class="uk-margin">
                            <button id="submitButton" type="button" class="uk-button uk-button-primary">ارسال</button>
                            &nbsp;&nbsp;
                            <span id="checkIcon" class="uk-margin-small-right" uk-icon="check"
                                  style="color: mediumspringgreen; display: none;"></span>

                        </div>
                        <div>
                            <c:if test="submittedForm">
                                <jsp:useBean id="status"/>

                                <h6>${status ? "Successfully added " : "Failed to add "}a email!</h6>
                            </c:if>
                        </div>
                    </fieldset>
                </form:form>
            </div>

        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/add/email.js"></script>
<%@include file="../components/scripts.jsp" %>
</body>
</html>

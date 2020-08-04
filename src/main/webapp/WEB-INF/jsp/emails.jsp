emails.jsp<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>emails</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>

<div class="bodyContainer">
    <%@include file="components/navigation.jsp" %>
    <div class="mainBody">
        <c:if test="${email.id != null}">

            <div class="cards">
                <div class="centered cardContainer"
                     uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                    <div class="uk-card uk-card-default">

                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle" uk-grid>
                                <div class="uk-wiukh-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">email
                                        #${email.id}</h3>
                                </div>
                            </div>
                        </div>

                        <div class="uk-card-body">
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">موضوع</span>
                                    ${email.subject}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">شرح پیام</span>
                                    ${email.message}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">فایل های محتوا</span>
                                    ${email.attachment}
                            </div>


                            <c:if test="email.allergicTo != '' && email.allergicTo != null">
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted">Allergic to</span>
                                    <span style="text-transform: capitalize">${email.emailAllergicTo}</span>
                                </div>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>


        </c:if>
        <c:if test="${email.emailId == null}">
            <h4>All emails</h4>
            <div class="ui cards">
                <c:forEach var="emailEntry" items="${emails}">
                    <div class="centered cardContainer"
                         uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                        <div class="uk-card uk-card-default">
                            <div class="uk-card-body">
                                <div class="uk-card-badge uk-label">${emailDate}</div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">موضوع</span>
                                        ${email.subject}
                                </div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">شرح پیام</span>
                                        ${email.message}
                                </div>

                            </div>
                            <div class="uk-card-footer">
                                <a href="/emails/${emailEntry.emailId}" class="uk-button uk-button-default">نمایش
                                    پیام</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>

<%@include file="components/scripts.jsp" %>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>leaves</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>

<div class="bodyContainer">
    <%@include file="components/navigation.jsp" %>
    <div class="mainBody">
        <c:if test="${leave.id != null}">

            <div class="cards">
                <div class="centered cardContainer"
                     uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                    <div class="uk-card uk-card-default">

                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle" uk-grid>
                                <div class="uk-wiukh-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">leave
                                        #${leave.id}</h3>
                                </div>
                            </div>
                        </div>

                        <div class="uk-card-body">
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">مرخصی</span>
                                    ${leave.leaveSubject}
                            </div>

                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">شرح مرخصی</span>
                                    ${leave.leaveMessage}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">شروع</span>
                                    ${leave.leaveFrom}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">پایان</span>
                                    ${leave.leaveTo}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">مدت</span>
                                    ${leave.leaveType}
                            </div>

                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">وضعیت</span>
                                    ${leave.leaveStatus}
                            </div>



                            <c:if test="leave.allergicTo != '' && leave.allergicTo != null">
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted">Allergic to</span>
                                    <span style="text-transform: capitalize">${leave.leaveAllergicTo}</span>
                                </div>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>


        </c:if>
        <c:if test="${leave.leaveId == null}">
            <h4>All leaves</h4>
            <div class="ui cards">
                <c:forEach var="leaveEntry" items="${leaves}">
                    <div class="centered cardContainer"
                         uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                        <div class="uk-card uk-card-default">
                            <div class="uk-card-body">
                                <div class="uk-card-badge uk-label">${leaveDate}</div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">مرخصی</span>
                                        ${leave.leaveSubject}
                                </div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">شرج مرخصی</span>
                                        ${leave.leaveMessage}
                                </div>


                            <div class="uk-card-footer">
                                <a href="/leaves/${leaveEntry.leaveId}" class="uk-button uk-button-default">نمایش
                                    مرخصی</a>
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

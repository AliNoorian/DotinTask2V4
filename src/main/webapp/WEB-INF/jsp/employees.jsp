<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>employees</title>
    <%@include file="components/stylesheets.jsp" %>
</head>
<body>

<div class="bodyContainer">
    <%@include file="components/navigation.jsp" %>
    <div class="mainBody">
        <c:if test="${employee.id != null}">

            <div class="cards">
                <div class="centered cardContainer"
                     uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                    <div class="uk-card uk-card-default">

                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle" uk-grid>
                                <div class="uk-wiukh-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">employee
                                        #${employee.id}</h3>
                                </div>
                            </div>
                        </div>

                        <div class="uk-card-body">
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نام</span>
                                    ${employee.firstName}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نام خانوادگی</span>
                                    ${employee.lastName}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">ایمیل</span>
                                    ${employee.email}
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نقش</span>
                                <span style="text-transform: capitalize">${employee.employeeRole}</span>
                            </div>
                            <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">مدیر</span>
                                    ${employee.manager}
                            </div>


                            <c:if test="employee.allergicTo != '' && employee.allergicTo != null">
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted">Allergic to</span>
                                    <span style="text-transform: capitalize">${employee.employeeAllergicTo}</span>
                                </div>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>


        </c:if>
        <c:if test="${employee.employeeId == null}">
            <h4>All employees</h4>
            <div class="ui cards">
                <c:forEach var="employeeEntry" items="${employees}">
                    <div class="centered cardContainer"
                         uk-scrollspy="cls: uk-animation-fade; target: .uk-card; delay: 250; repeat: true">
                        <div class="uk-card uk-card-default">
                            <div class="uk-card-body">
                                <div class="uk-card-badge uk-label">${employeeDate}</div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نام</span>
                                        ${employee.firstName}
                                </div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نام خانوادگی</span>
                                        ${employee.lastName}
                                </div>
                                <div>
                                <span style="display: inline-block"
                                      class="uk-text-muted uk-text-small uk-text-uppercase">نقش</span>
                                    <span style="text-transform: capitalize">${employee.employeeRole}</span>
                                </div>
                            </div>
                            <div class="uk-card-footer">
                                <a href="/employees/${employeeEntry.employeeId}" class="uk-button uk-button-default">نمایش
                                    کارمند</a>
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

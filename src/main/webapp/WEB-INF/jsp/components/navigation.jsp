<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="navbar-right">

    <div class="ui secondary  menu" style="height: 1rem;" dir="rtl">
        <a href="${pageContext.request.contextPath}/"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/' ? 'active' : ''}">
            صفحه اصلی
        </a>
        <a href="${pageContext.request.contextPath}/employees/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/employees/list' ? 'active' : ''}">
            کارمندان
        </a>
        <a href="${pageContext.request.contextPath}/emails/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/emails/list' ? 'active' : ''}">
            پیام های دریافتی
        </a>
        <a href="${pageContext.request.contextPath}/emails/sent"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/emails/sent' ? 'active' : ''}">
            پیام های ارسالی
        </a>
        <a href="${pageContext.request.contextPath}/leaves/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/leaves/list' ? 'active' : ''}">
            درخواست های مرخصی
        </a>

        <div class="dropdown-item" uk-dropdown="pos: bottom-right">
        </div>
        <div class="right menu centered">
            <a href="#" class="item">ایجاد&nbsp;+</a>
            <div class="uk-navbar-dropdown uk-margin-remove" uk-dropdown="pos: bottom-right">
                <ul class="uk-nav uk-navbar-dropdown-nav">
                    <li><a href="${pageContext.request.contextPath}/employees/showFormForAdd">کارمند</a></li>
                    <li><a href="${pageContext.request.contextPath}/emails/showFormForAdd">ایمیل</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaves/showFormForAdd">مرخصی</a></li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="item">خروج</a>
        </div>
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="logo" align="left"/>
    </div>

</div>



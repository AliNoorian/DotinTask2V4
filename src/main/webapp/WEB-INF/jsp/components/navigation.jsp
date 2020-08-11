<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>

<nav dir="rtl">
    <div class="ui secondary  menu" style="height: 1rem;">
        <a href="/" class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/' ? 'active' : ''}">
            صفحه اصلی
        </a>
        <a href="/employees/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/employees/list' ? 'active' : ''}">
            کارمندان
        </a>
        <a href="/emails/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/emails/list' ? 'active' : ''}">
            پیام ها
        </a>
        <a href="/leaves/list"
           class="item ${requestScope['javax.servlet.forward.request_uri'] eq '/leaves/list' ? 'active' : ''}">
            مرخصی ها
        </a>
        <a class="item">
            موارد دیگر
        </a>
        <div class="uk-navbar-dropdown uk-margin-remove" uk-dropdown="pos: bottom-right">
        </div>
        <div class="right menu centered">
            <a href="#" class="item">ایجاد&nbsp;+</a>
            <div class="uk-navbar-dropdown uk-margin-remove" uk-dropdown="pos: bottom-right">
                <ul class="uk-nav uk-navbar-dropdown-nav">
                    <li><a href="/employees/showFormForAdd">کارمند</a></li>
                    <li><a href="/emails/showFormForAdd">ایمیل</a></li>
                    <li><a href="/leaves/showFormForAdd">مرخصی</a></li>
                </ul>
            </div>
            <a href="/logout" class="item">خروج</a>
        </div>
    </div>
</nav>
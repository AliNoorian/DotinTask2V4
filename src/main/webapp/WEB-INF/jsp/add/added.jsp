<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${status ? "Success" : "Failure"}</title>
    <%@include file="../components/stylesheets.jsp"%>
</head>
<body>

<div class="bodyContainer">
    <%@include file="../components/navigation.jsp"%>
    <main class="main">
        <div class="mainBody">

            <div class="centered addedContainer">
                <h4>${status ? "Successfully added a " : "Failed to add a "}${modelName}.</h4>
            </div>
            <div class="centered">
                <button class="uk-button uk-button-primary">Return</button>
            </div>
        </div>
    </main>
</div>

<%@include file="../components/scripts.jsp"%>
</body>
</html>

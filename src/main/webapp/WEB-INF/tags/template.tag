<%@ tag description="Template Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype>
<html>
    <head>
        <style><%@include file="/resources/css/main.css"%></style>
        <%--<link type="text/css" href="<c:url value="/resources/css/main.css"/>"/>--%>

        <title>Картотека книг</title>
    </head>

    <body>
        <div class="content">
            <!--<div class="contentBody">-->
                <h1>Картотека книг</h1>
                    <jsp:doBody />
            <!--</div>-->
        </div>
    </body>
</html>
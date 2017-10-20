<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">
    <c:choose>
        <c:when test="${empty sessionScope.get('user').role}">
        </c:when>
        <c:otherwise>
            <a href="<c:url value="/site/${fn:toLowerCase(sessionScope.get('user').role)}"/>">На главную</a>
        </c:otherwise>
    </c:choose>
    <a href="<c:url value="/"/>">Выйти</a>
</div>
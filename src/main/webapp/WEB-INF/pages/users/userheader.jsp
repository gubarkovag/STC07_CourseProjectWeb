<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header">
        <sec:authorize access="hasAnyRole('ADMIN','USER')">
            <a href="<c:url value="/site/${sessionScope.get('user').role}"/>">На главную</a>
        </sec:authorize>
    <a href="<c:url value="/j_spring_security_logout"/>">Выйти</a>

    <%--<sec:authentication property="principal.authorities" var="authorities" />
    <c:forEach items="${authorities}" var="authority" varStatus="vs">
        <p>${authority.authority}</p>
    </c:forEach>--%>
</div>
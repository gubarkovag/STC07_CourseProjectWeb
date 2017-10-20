<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="userheader.jsp"/>
    <div class="contentBody">
        <a class="knopka" href="<c:url value="/site/addUser"/>">Добавить пользователя</a>
        <a class="knopka" href="<c:url value="/site/users"/>">Редактировать пользователей</a>
        <a class="knopka" href="<c:url value="/site/adminViewApplications"/>">Просмотр заявок</a>
    </div>
</t:template>
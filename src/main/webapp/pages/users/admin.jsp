<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="adminheader.jsp"/>
    <div class="contentBody">
        <a class="knopka" href="<c:url value="adduser"/>">Добавить пользователя</a>
        <a class="knopka" href="<c:url value="users"/>">Редактировать пользователей</a>
        <a class="knopka" href="<c:url value="adminviewapps"/>">Просмотр заявок</a>
    </div>
</t:template>
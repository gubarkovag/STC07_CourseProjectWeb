<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="userheader.jsp"/>
    <div class="contentBody">
        <a class="knopka" href="<c:url value="/site/addApplication"/>">Добавить заявку</a>
        <a class="knopka" href="<c:url value="/site/viewRejectedApplications"/>">Просмотр отклонённых заявок</a>
    </div>
</t:template>
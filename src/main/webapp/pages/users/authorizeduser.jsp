<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="authuserheader.jsp"/>
    <div class="contentBody">
        <a class="knopka" href="<c:url value="addapplication"/>">Добавить заявку</a>
        <%--<a class="knopka" href="<c:url value="viewRejectedApps"/>">Просмотр отклонённых заявок</a>--%>
    </div>
</t:template>
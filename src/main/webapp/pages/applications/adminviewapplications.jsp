<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="../users/userheader.jsp"/>
    <div class="contentBody">
        <c:choose>
            <c:when test="${empty applications}">
                <h2 class="error">Заявки отсутствуют</h2>
            </c:when>
            <c:otherwise>
                <table class="application-list-table">
                    <tr>
                        <th>Название</th>
                        <th>Жанр</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${applications}" var="application">
                        <tr>
                            <td>${application.name}</td>
                            <td>${application.genre}</td>
                            <td><a href="<c:url value="/site/approveApplication?id=${application.id}"/>">Принять</a></td>
                            <td><a href="<c:url value="/site/rejectApplication?id=${application.id}"/>">Отклонить</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</t:template>
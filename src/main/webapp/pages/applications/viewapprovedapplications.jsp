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
                    </tr>
                    <c:forEach items="${applications}" var="application">
                        <tr>
                            <td>${application.name}</td>
                            <td>${application.genre}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</t:template>
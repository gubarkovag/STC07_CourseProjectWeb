<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <jsp:include page="userheader.jsp"/>
    <div class="contentBody">
        <h2>Регистрация нового пользователя</h2>
        <c:choose>
            <c:when test="${empty wrongReg}">
            </c:when>
            <c:otherwise>
                <h3 class="error">${wrongReg}</h3>
            </c:otherwise>
        </c:choose>
        <form id="addUserForm" method="post" action="<c:url value="/site/addUser"/>">
            <table>
                <tr>
                    <td>
                        <label>Логин: </label>
                    </td>
                    <td>
                        <input type="text" name="login" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Пароль: </label>
                    </td>
                    <td>
                        <input type="password" name="password" required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Регистрация"/>
                        <%--<a href="<c:url value="/addUser"/>">Регистрация</a>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:template>
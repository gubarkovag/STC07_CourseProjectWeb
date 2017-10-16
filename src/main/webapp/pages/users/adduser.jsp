<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <div class="contentBody">
        <h2>Регистрация нового пользователя</h2>
        <c:choose>
            <c:when test="${empty isUserExistMessage}">
            </c:when>
            <c:otherwise>
                <h3 class="error">${isUserExistMessage}</h3>
            </c:otherwise>
        </c:choose>
        <form id="addUserForm" method="post" action="<c:url value=""/>">
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
                        <a href="<c:url value="/adduser"/>">Регистрация</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:template>
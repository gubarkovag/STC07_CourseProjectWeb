<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="../../pages/applications/viewappsheader.jsp"/>
    <div class="contentBody">
        <c:choose>
            <c:when test="${empty authInfo}">
                <h3 class="error">Введите данные пользователя</h3>
            </c:when>
            <c:otherwise>
                <h3 class="error">${authInfo}</h3>
            </c:otherwise>
        </c:choose>
        <form id="authForm" method="post" action="<c:url value="/appLogin"/>">
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
                    <td>
                        <input type="submit" value="Вход"/>
                    </td>
                    <td>
                        <a href="<c:url value="/site/addUser"/>">Регистрация</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:template>
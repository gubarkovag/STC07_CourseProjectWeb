<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:include page="userheader.jsp"/>
    <div class="contentBody">
        <form id="editUserForm" method="post" action="<c:url value="/site/editUser"/>">
            <input type="hidden" name="id" value="${user.id}"/>
            <table>
                <tr>
                    <td>
                        <label>Логин: </label>
                    </td>
                    <td>
                        <input type="text" name="username" value="${user.username}" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Пароль: </label>
                    </td>
                    <td>
                        <input type="password" name="password" value="${user.password}" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Роль: </label>
                    </td>
                    <td>
                        <select name="role">
                            <option value="admin">Администратор</option>
                            <option value="authorizeduser">Авторизованный пользователь</option>
                            <option value="nonauthorizeduser">Неавторизованный пользователь</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Редактировать"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:template>
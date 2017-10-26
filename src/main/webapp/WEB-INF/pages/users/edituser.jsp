<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:include page="userheader.jsp"/>
    <div class="contentBody">
        <form:form method="post" action="/courseprojectweb/site/editUser" commandName="userdata">
            <form:hidden path="id"/>
            <table>
                <tr>
                    <td>
                        <form:label path="login">
                            Логин
                        </form:label>
                    </td>
                    <td>
                        <form:input value="${userdata.login}" path="login" />
                    </td>
                    <td><form:errors cssClass="error" path="login"></form:errors></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">
                            Пароль
                        </form:label>
                    </td>
                    <td>
                        <form:input value="${userdata.password}" path="password" />
                    </td>
                    <td><form:errors cssClass="error" path="password"></form:errors></td>
                </tr>
                <td>
                    <form:label path="role">
                        Роль
                    </form:label>
                </td>
                <td>
                    <form:select path="role">
                        <form:option value="ADMIN" label="Администратор" />
                        <form:option value="AUTHORIZEDUSER" label="Авторизованный пользователь" />
                        <form:option value="NONAUTHORIZEDUSER" label="Неавторизованный пользователь" />
                    </form:select>
                </td>
                <tr>
                    <td colspan="2"><input type="submit" value="Редактировать" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</t:template>
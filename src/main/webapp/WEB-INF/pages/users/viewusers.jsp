<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
  <jsp:include page="userheader.jsp"/>
  <div class="contentBody">
    <c:choose>
  <c:when test="${empty users}">
    <h2 class="error">Пользователи отсутствуют</h2>
  </c:when>
  <c:otherwise>
    <table class="application-list-table">
      <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Роль</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
      </tr>
      <c:forEach items="${users}" var="user">
        <tr>
          <td>${user.login}</td>
          <td>${user.password}</td>
          <td>${user.role}</td>
          <td><a href="<c:url value="/site/editUser?id=${user.id}"/>">Редактировать</a></td>
          <td><a href="<c:url value="/site/deleteUser?id=${user.id}"/>">Удалить</a></td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
  </c:choose>
    </div>
</t:template>
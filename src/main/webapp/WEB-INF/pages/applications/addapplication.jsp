<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
  <jsp:include page="../users/userheader.jsp"/>
  <div class="contentBody">
    <form:form method="post" action="/courseprojectweb/site/addApplication" commandName="bookdata">
      <table>
        <tr>
          <td>
            <form:label path="name">
              Название
            </form:label>
          </td>
          <td>
            <form:input path="name" />
          </td>
          <td><form:errors cssClass="error" path="name"></form:errors></td>
        </tr>
        <tr>
          <td>
            <form:label path="genre">
              Жанр
            </form:label>
          </td>
          <td>
            <form:input path="genre" />
          </td>
          <td><form:errors cssClass="error" path="genre"></form:errors></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="Добавить" /></td>
        </tr>
      </table>
    </form:form>
  </div>
</t:template>
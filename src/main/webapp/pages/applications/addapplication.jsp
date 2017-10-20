<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
  <jsp:include page="../users/userheader.jsp"/>
  <div class="contentBody">
    <form method="post" action="/site/addApplication">
    <table>
      <tr>
        <td>
          <label>Название: </label>
        </td>
        <td>
          <input type="text" name="name" required/>
        </td>
      </tr>
      <tr>
        <td>
          <label>Жанр: </label>
        </td>
        <td>
          <input type="text" name="genre"/>
        </td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Добавить" /></td>
      </tr>
    </table>
  </form>
  </div>
</t:template>
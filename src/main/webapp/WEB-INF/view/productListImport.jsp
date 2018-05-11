<%--
  Created by IntelliJ IDEA.
  User: mantas
  Date: 18.5.11
  Time: 15.13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="common/header.jsp"%>
    <c:url value="/admin/importProductExcel/excelFile" var="uploadFileUrl" />
    <form method="post" enctype="multipart/form-data" action="${uploadFileUrl}?${_csrf.parameterName}=${_csrf.token}">
        <div class="file-input"><input type="file" name="file" accept=".xls,.xlsx" /></div>
        <div class="file-submit-button"><input type="submit" value="Import file" /></div>
    </form>
<%@ include file="common/footer.jsp"%>
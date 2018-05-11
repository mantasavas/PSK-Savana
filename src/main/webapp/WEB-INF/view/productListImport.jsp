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


<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Import product data from Excel file</h1>
            <h3> Choose file </h3>
            <c:url value="/admin/importProductExcel/excelFile" var="uploadFileUrl" />
            <form method="post" enctype="multipart/form-data" action="${uploadFileUrl}?${_csrf.parameterName}=${_csrf.token}">
                <p class="file-input"><input type="file" name="file" accept=".xls,.xlsx" /></p>
                <p class="file-submit-button"><button class="btn btn-warning" type="submit">Import File</button></p>
            </form>
        </div>
<%@ include file="common/footer.jsp"%>
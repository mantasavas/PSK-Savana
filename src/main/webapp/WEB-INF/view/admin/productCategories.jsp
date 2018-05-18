<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product categories</h1>
            <p class="lead">Fill the information below to add new category...</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/productCategories?${_csrf.parameterName}=${_csrf.token}"
                   method="post" modelAttribute="productCategory" enctype="multipart/form-data">

        <div class="form-group">
            <h4>Current categories:</h4>
            <ul>
                <c:if test="${empty categories}">
                    <h5>There are no categories yet!</h5>
                </c:if>
                <c:forEach items="${categories}" var="categoryName">
                    <li>${categoryName}</li>
                </c:forEach>
            </ul>
        </div>

        <h4>Add new category:</h4>

        <div class="form-group">
            <label for="categoryName">Category name</label>
            <form:input path="productCategoryName" id="categoryName" class="form-Control"/>
            <form:errors path="productCategoryName" cssStyle="color: red" />
        </div>

        <div class="form-group">
            <label class="control-label" for="productCategoryImage">Upload Picture</label>
            <form:input id="productCategoryImage" path="productCategoryImage" type="file" class="form:input-large" />
        </div>

        <br>
        <br>
        <input type="submit" value="Submit" class="btn btn-primary">
        <a href="<c:url value="/admin"/>" class="btn btn-secondary">Back</a>

        </form:form>


<%@ include file="../common/footer.jsp"%>
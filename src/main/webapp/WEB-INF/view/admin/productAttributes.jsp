<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product categories</h1>
            <p class="lead">Fill the information below to add new category...</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/productAttributes?${_csrf.parameterName}=${_csrf.token}"
                   method="post" modelAttribute="attribute" enctype="multipart/form-data">

        <div class="form-group">
            <h4>Current attributes:</h4>
            <ul>
                <c:if test="${empty attributes}">
                    <h5>There are no attributes yet!</h5>
                </c:if>
                <c:forEach items="${attributes}" var="attribute">
                    <li>${attribute.attributeKey}</li>
                </c:forEach>
            </ul>
        </div>

        <h4>Add new attribute:</h4>

        <div class="form-group">
            <label for="attributeKey">Attribute name</label>
            <form:input path="attributeKey" id="attributeKey" class="form-Control"/>
            <form:errors path="attributeKey" cssStyle="color: red" />
        </div>

        <br>
        <br>
        <input type="submit" value="Add" class="btn btn-primary">
        <a href="<c:url value="/admin"/>" class="btn btn-secondary">Back</a>

        </form:form>


<%@ include file="../common/footer.jsp"%>
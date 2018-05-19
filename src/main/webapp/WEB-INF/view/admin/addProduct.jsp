<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Add Product</h1>
            <p class="lead">Fill the information below to add new product...</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/product/addProduct?${_csrf.parameterName}=${_csrf.token}"
                   method="post" modelAttribute="product" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Name</label> <form:errors path="productName" cssStyle="color: red" />
            <form:input path="productName" id="name" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="category">Category</label>
            <c:if test="${empty categories}">
                <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category" value="Other"/>Other</label>
            </c:if>
            <c:forEach items="${categories}" var="categoryName">
                <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category" value="${categoryName}"/>${categoryName}</label>
            </c:forEach>
            <form:errors path="productCategory" cssStyle="color: red" />
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="productDescription" id="description" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="price">Price</label> <form:errors path="productPrice" cssStyle="color: red" />
            <form:input path="productPrice" id="price" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status"
                                                             value="active"/>Active</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status" value="inactive"/>Inactive</label>
        </div>

        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <form:input path="productManufacturer" id="manufacturer" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="discountPercentage">Discount (%)</label><form:errors path="productDiscountPercentage" cssStyle="color: red" />
            <form:input path="productDiscountPercentage" id="discountPercentage" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="discountExpirationDate">Discount expiration date (yyyy-mm-dd hh:mm:ss)</label><form:errors path="productDiscountExpirationDatetime" cssStyle="color: red" />
            <form:input path="productDiscountExpirationDatetime" id="discountExpirationDate" class="form-Control"/>
        </div>

        <br>

        <label>Attributes</label>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="var" var="attribute" items="${attributes}" >
                <tr>
                    <td>${attribute.attributeKey}</td>
                    <td>
                        <form:input path="productAttributes[${var.index}].attributeValue" id="productAttribute" class="form-Control"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form:errors path="productAttributes" cssStyle="color: red" />

        <br>

        <div class="form-group">
            <label class="control-label" for="productImage">Upload Picture</label>
            <form:input id="productImage" path="productImage" type="file" class="form:input-large" />
        </div>

        <br>
        <br>
        <input type="submit" value="Submit" class="btn btn-primary">
        <a href="<c:url value="/admin/inventory"/>" class="btn btn-secondary">Cancel</a>

        </form:form>


<%@ include file="../common/footer.jsp"%>
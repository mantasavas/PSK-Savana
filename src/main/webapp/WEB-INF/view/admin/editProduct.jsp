<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Edit Product</h1>
            <p class="lead">You can update the information about product here</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/product/editProduct?${_csrf.parameterName}=${_csrf.token}"
                   method="post" modelAttribute="product" enctype="multipart/form-data">
        <form:hidden path="productId" value="${product.productId}" />
        <div class="form-group">
            <label for="name">Name</label> <form:errors path="productName" cssStyle="color: red" />
            <form:input path="productName" id="name" class="form-Control" value="${product.productName}"/>
        </div>

        <div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category"
                                                             value="keyboard"/>Keyboard</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category"
                                                             value="mouse"/>Mouse</label>
            <label class="checkbox-inline"><form:radiobutton path="productCategory" id="category"
                                                             value="other"/>Other</label>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="productDescription" id="description" class="form-Control" value="${product.productDescription}"/>
        </div>

        <div class="form-group">
            <label for="price">Price</label> <form:errors path="productPrice" cssStyle="color: red" />
            <form:input path="productPrice" id="price" class="form-Control" value="${product.productPrice}"/>
        </div>

        <div class="form-group">
            <label for="condition">Condition</label>
            <label class="checkbox-inline"><form:radiobutton path="productCondition" id="condition"
                                                             value="new"/>New</label>
            <label class="checkbox-inline"><form:radiobutton path="productCondition" id="condition" value="used"/>Used</label>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status"
                                                             value="active"/>Active</label>
            <label class="checkbox-inline"><form:radiobutton path="productStatus" id="status" value="inactive"/>Inactive</label>
        </div>

        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <form:input path="productManufacturer" id="manufacturer" class="form-Control" value="${product.productManufacturer}"/>
        </div>

        <div class="form-group">
            <label class="control-label" for="productImage">Upload Picture</label>
            <form:input id="productImage" path="productImage" type="file" class="form:input-large" />
        </div>

        <br>
        <br>
        <input type="submit" value="Submit" class="btn btn-primary">
        <a href="<c:url value="/admin/productInventory"/>" class="btn btn-secondary">Cancel</a>

        </form:form>


<%@ include file="../common/footer.jsp"%>
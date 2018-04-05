<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Add Product</h1>
            <p class="lead">Fill the information below to add new product...</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/productInventory/addProduct"
                   method="post" modelAttribute="product" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="name" id="name" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="category">Category</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="keyboard"/>Keyboard</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="mouse"/>Mouse</label>
            <label class="checkbox-inline"><form:radiobutton path="category" id="category"
                                                             value="other"/>Other</label>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="description" id="description" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <form:input path="price" id="price" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="condition">Condition</label>
            <label class="checkbox-inline"><form:radiobutton path="condition" id="condition"
                                                             value="new"/>New</label>
            <label class="checkbox-inline"><form:radiobutton path="condition" id="condition" value="mouse"/>Used</label>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <label class="checkbox-inline"><form:radiobutton path="status" id="status"
                                                             value="active"/>Active</label>
            <label class="checkbox-inline"><form:radiobutton path="status" id="status" value="inactive"/>Inactive</label>
        </div>

        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <form:input path="manufacturer" id="manufacturer" class="form-Control"/>
        </div>

        <div class="form-group">
            <label class="control-label" for="productImage">Upload Picture</label>
            <form:input id="productImage" path="image" type="file" class="form:input-large" />
        </div>

        <br>
        <br>
        <input type="submit" value="Submit" class="btn btn-primary">
        <a href="<c:url value="/admin/productInventory"/>" class="btn btn-secondary">Cancel</a>

        </form:form>


<%@ include file="common/footer.jsp"%>
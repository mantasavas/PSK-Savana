<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product inventory (admin)</h1>
            <p class="lead">This is product inventory page</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Proto Thumb</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><img src="<c:url value="/resources/images/${product.id}.png" /> " alt="image"
                             style="width: 100%"/></td>
                    <td>${product.name}</td>
                    <td>${product.category}</td>
                    <td>${product.condition}</td>
                    <td>$${product.price}</td>
                    <td>
                        <a href="<spring:url value="/products/product/${product.id}"/>" class="btn btn-primary">Info</a>
                        <a href="<spring:url value="/admin/productInventory/deleteProduct/${product.id}"/>" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="<spring:url value="/admin/productInventory/addProduct"/>" class="btn btn-primary">Add new product</a>

<%@ include file="common/footer.jsp"%>
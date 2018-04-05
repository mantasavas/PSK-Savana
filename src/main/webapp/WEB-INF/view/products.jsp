<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>All Products</h1>
            <p class="lead">Checkout all the awesome products available now!</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Image</th>
                <th>Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><img src="<c:url value="/resources/product-images/${product.id}.png" /> " alt="image"
                             style="width: 100%"/></td>
                    <td>${product.name}</td>
                    <td>${product.category}</td>
                    <td>${product.condition}</td>
                    <td>$${product.price}</td>
                    <td><a href="<spring:url value="/products/product/${product.id}"/>" class="btn btn-primary">Info</a></td>
                </tr>
            </c:forEach>
        </table>

<%@ include file="common/footer.jsp"%>
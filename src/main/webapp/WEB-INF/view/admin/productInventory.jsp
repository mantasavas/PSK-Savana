<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/header.jsp"%>

<script>
    $(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,5,10,30,-1], [1,5,10,30,"All"]],
            "iDisplayLength": 5
        });
    });
</script>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product inventory (admin)</h1>
            <p class="lead">This is product inventory page</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th width="20%">Photo</th>
                <th width="15%">Product Name</th>
                <th width="15%">Category</th>
                <th width="15%">Condition</th>
                <th width="10%">Price</th>
                <th width="25%"></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><img src="<c:url value="/resources/images/${product.productId}.png" /> " alt="image"
                             style="width: 50%"/></td>
                    <td >${product.productName}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productCondition}</td>
                    <td>$${product.productPrice}</td>
                    <td>
                        <a href="<spring:url value="/product/viewProduct/${product.productId}"/>" class="btn btn-primary">Info</a>
                        <a href="<spring:url value="/admin/product/deleteProduct/${product.productId}"/>" class="btn btn-danger">Delete</a>
                        <a href="<spring:url value="/admin/product/editProduct/${product.productId}"/>" class="btn btn-warning">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="<spring:url value="/admin/product/addProduct"/>" class="btn btn-primary">Add new product</a>

<%@ include file="../common/footer.jsp"%>
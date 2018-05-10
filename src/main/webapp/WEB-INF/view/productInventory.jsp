<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/header.jsp"%>

<script>
    $(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,2,3,5,10, -1], [1,2,3,5,10, "All"]]
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
                    <td><img src="<c:url value="/resources/images/${product.productId}.png" /> " alt="image"
                             style="width: 100%"/></td>
                    <td>${product.productName}</td>
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

<%@ include file="common/footer.jsp"%>
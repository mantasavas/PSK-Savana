<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/header.jsp"%>

<jsp:useBean id="now" class="java.util.Date" />

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
                <th width="10%">Condition</th>
                <th width="20%">Price</th>
                <th width="20%"></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <fmt:parseDate value="${product.productDiscountExpirationDatetime}" var="discountExpire" pattern="yyyy-MM-dd HH:mm:ss" />
                <tr>
                    <td>
                        <c:if test="${product.productImage == null || product.productImage.size == 0}">
                            <img src="<c:url value="/resources/images/default.png" /> " alt="image"
                                 style="width: 50%"/>
                        </c:if>
                        <c:if test="${product.productImage != null && product.productImage.size != 0}">
                            <img src="<c:url value="/resources/images/${product.productId}.png" /> " alt="image"
                                 style="width: 50%"/>
                        </c:if>
                    </td>
                    <td >${product.productName}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productCondition}</td>
                    <c:if test="${discountExpire > now}">
                        <td>
                            $${product.actualPrice}
                            <c:if test="${product.productDiscountPercentage > 0}">
                                <span style="text-decoration: line-through;">$${product.productPrice}</span>
                                <span style="color: red;">-${product.productDiscountPercentage}%</span>
                            </c:if>
                        </td>
                    </c:if>
                    <c:if test="${discountExpire <= now}">
                        <td>$${product.productPrice}</td>
                    </c:if>
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
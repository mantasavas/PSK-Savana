<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/header.jsp"%>

<jsp:useBean id="now" class="java.util.Date" />

<script>
    $(document).ready(function(){
        var searchCondition = '${searchCondition}';

        $('.table').DataTable({
            "lengthMenu": [[1,5,10,30,-1], [1,5,10,30,"All"]],
            "iDisplayLength": 5,
            "oSearch" : {"sSearch": searchCondition}
        });
    });
</script>

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
                    <td>${product.productName}</td>
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
                    <td><a href="<spring:url value="/product/viewProduct/${product.productId}"/>" class="btn btn-primary">Info</a></td>
                </tr>
            </c:forEach>
        </table>

<%@ include file="common/footer.jsp"%>
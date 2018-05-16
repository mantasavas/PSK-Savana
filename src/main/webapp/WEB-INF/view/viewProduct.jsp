<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/header.jsp"%>

<jsp:useBean id="now" class="java.util.Date" />

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product</h1>
            <p class="lead">Information about the product!</p>
        </div>
        <div class="container" ng-app="cartApp">
            <div class="row">
                <fmt:parseDate value="${product.productDiscountExpirationDatetime}" var="discountExpire" pattern="yyyy-MM-dd HH:mm:ss" />
                <div class="col-md-5">
                    <c:if test="${product.productImage == null || product.productImage.size == 0}">
                        <img src="<c:url value="/resources/images/default.png" /> " alt="image"
                             style="width: 50%"/>
                    </c:if>
                    <c:if test="${product.productImage != null && product.productImage.size != 0}">
                        <img src="<c:url value="/resources/images/${product.productId}.png" /> " alt="image"
                             style="width: 50%"/>
                    </c:if>
                </div>
                <div class="col-md-5">
                    <h3>${product.productName}</h3>
                    <p>${product.productDescription}</p>
                    <p><strong>Manufacturer: </strong>${product.productManufacturer}</p>
                    <p><strong>Category: </strong>${product.productCategory}</p>
                    <p><strong>Condition: </strong>${product.productCondition}</p>
                    <c:if test="${discountExpire > now}">
                        <td>
                            <p><strong>Price: </strong>$${product.actualPrice}
                            <c:if test="${product.productDiscountPercentage > 0}">
                                <span style="text-decoration: line-through;">$${product.productPrice}</span>
                                <p><strong>Discount: </strong><span style="color: red">-${product.productDiscountPercentage}%</span></p>
                            </c:if>
                            </p>
                        </td>
                    </c:if>
                    <c:if test="${discountExpire <= now}">
                        <td><strong>Price: </strong>$${product.productPrice}</td>
                        <br>
                    </c:if>
                    <br>

                    <c:set var="role" scope="page" value="${param.role}"/>
                    <c:set var="url" scope="page" value="/product/products"/>
                    <c:if test="${role='admin'}">
                        <c:set var="url" scope="page" value="/admin/inventory" />
                    </c:if>

                    <p ng-controller="cartCtrl">
                        <a href="<c:url value="${url}" />" class="btn btn-secondary">Back</a>
                        <a href="#" class="btn btn-primary btn-large" ng-click="addToCart('${product.productId}')"><span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</a>
                        <a href="<spring:url value="/customer/cart/"/>" class="btn btn-success"><span class="glyphicon glyphicon-hand-right"></span>View Cart</a>
                    </p>
                </div>
            </div>
        </div>

<!-- Angular js controller -->
<script src="<c:url value="/resources/js/controller.js" /> "></script>
<%@ include file="common/footer.jsp"%>
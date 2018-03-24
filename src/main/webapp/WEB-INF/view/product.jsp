<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Product</h1>
            <p class="lead">Information about the product!</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="#" alt="image" style="width: 100%; height: 300px"/>
                </div>
                <div class="col-md-5">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p><strong>Manufacturer: </strong>${product.manufacturer}</p>
                    <p><strong>Category: </strong>${product.category}</p>
                    <p><strong>Condition: </strong>${product.condition}</p>
                    <p>$${product.price}</p>
                </div>
            </div>
        </div>

<%@ include file="common/footer.jsp"%>
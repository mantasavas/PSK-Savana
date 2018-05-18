<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Admin panel</h1>
            <p class="lead">This is only for administrator!</p>
        </div>

        <h3>
            <a href="<c:url value="/admin/inventory"/>" >Product Inventory</a>
        </h3>
        <p>Here you can view, check and modify product inventory</p>

        <br/>

        <h3>
            <a href="<c:url value="/admin/productCategories"/>" >Product Categories</a>
        </h3>
        <p>Here you can view existing and add new product categories</p>

        <br/>

        <h3>
            <a href="<c:url value="/admin/customers" /> ">Customer Management</a>
        </h3>
        <p>Here you can view and enable/disable customer accounts</p>

        <br/>

        <h3>
            <a href="<c:url value="/admin/orders" /> ">Order Management</a>
        </h3>
        <p>Here you can view and change status of orders</p>

<%@ include file="../common/footer.jsp"%>
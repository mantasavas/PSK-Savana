<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />

<script>
    $(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,5,10,30,-1], [1,5,10,30,"All"]],
            "iDisplayLength": 5
        });
    });
</script>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Orders</h1>

            <p class="lead">Here you can see your orders</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Date</th>
                <th>Total price</th>
                <%--<th>Items</th>--%>
                <th>Status</th>
                <th>Rating</th>
            </tr>
            </thead>
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${now}</td>
                    <td>$${order.cart.grandTotal}</td>
                    <%--<td>--%>
                        <%--<c:forEach items="${order.cart.cartItems}" var="item">--%>
                            <%--<h3>abc</h3>--%>
                            <%--<a>${item.quantity}</a>--%>
                        <%--</c:forEach>--%>
                    <%--</td>--%>
                    <%--<td>${order.customerName}</td>--%>
                    <td>Delivered</td>
                    <c:if test="${order.rating > 0}">
                        <td><a>${order.rating}</a></td>
                    </c:if>
                    <c:if test="${order.status.equals(\"delivered\") && order.rating == 0}">
                        <td><a href="<spring:url value="#"/>" class="btn btn-success">Rate</a></td>
                    </c:if>
                    <c:if test="${!order.status.equals(\"delivered\") && order.rating == 0}">
                        <td><a>-</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
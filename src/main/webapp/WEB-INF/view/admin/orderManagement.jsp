<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<!-- Star CSS -->
<link href="<c:url value="/resources/css/star.css" />" rel="stylesheet">

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
            <h1>Order Management</h1>

            <p class="lead">This is the order management page!</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th width="5%">ID</th>
                <th width="15%">Date</th>
                <th width="10%">Customer</th>
                <th width="10%">Total</th>
                <th width="10%">Status</th>
                <th width="10%">Rating</th>
                <th width="10%">Feedback</th>
                <th width="30%">Change status</th>
            </tr>
            </thead>
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.customerOrderId}</td>
                    <td>${order.orderDatetime}</td>
                    <td>${order.customer.customerEmail}</td>
                    <td>$${order.cart.grandTotal}</td>
                        <%--<td>--%>
                        <%--<c:forEach items="${order.cart.cartItems}" var="item">--%>
                        <%--<h3>abc</h3>--%>
                        <%--<a>${item.quantity}</a>--%>
                        <%--</c:forEach>--%>
                        <%--</td>--%>
                        <%--<td>${order.customerName}</td>--%>
                    <td>${order.status}</td>
                    <c:if test="${order.rating == 0}">
                        <td>-</td>
                    </c:if>
                    <c:if test="${order.rating > 0}">
                        <td>
                            <div class="container">
                                <div class="starrating risingstar d-flex justify-content-center">
                                    <c:forEach begin="1" end="${order.rating}" varStatus="loop">
                                        <label style="color: yellow"></label>
                                    </c:forEach>
                                    <c:forEach begin="${order.rating}" end="4" varStatus="loop">
                                        <label></label>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${order.feedback == null}">
                        <td>-</td>
                    </c:if>
                    <c:if test="${order.feedback != null}">
                        <td> <a style="color: inherit" href="<spring:url value="/admin/orders/${order.customerOrderId}/feedback"/>">${order.feedback}</a></td>
                    </c:if>
                    <td>
                        <a href="<spring:url value="/admin/orders/status/${order.customerOrderId}/Accepted"/>" class="btn btn-primary btn-sm">Accepted</a>
                        <a href="<spring:url value="/admin/orders/status/${order.customerOrderId}/In progress"/>" class="btn btn-secondary btn-sm">In progress</a>
                        <a href="<spring:url value="/admin/orders/status/${order.customerOrderId}/Sent"/>" class="btn btn-info btn-sm">Sent</a>
                        <a href="<spring:url value="/admin/orders/status/${order.customerOrderId}/Delivered"/>" class="btn btn-success btn-sm">Delivered</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />

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
            <h1>Orders</h1>

            <p class="lead">Here you can see your orders</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Date</th>
                <th>Total</th>
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
                    <td>${order.status}</td>
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
                    <c:if test="${order.status.equals(\"Delivered\") && order.rating == 0}">
                        <td>
                            <div class="container">
                                <div class="starrating risingstar d-flex justify-content-center flex-row-reverse">
                                    <input type="radio" id="star5" name="rating" value="5"
                                           onclick="location.href='<spring:url value="/customer/orders/rate/${order.customerOrderId}/5"/>'"/>
                                        <label for="star5" title="5 star"></label>
                                    <input type="radio" id="star4" name="rating" value="4"
                                           onclick="location.href='<spring:url value="/customer/orders/rate/${order.customerOrderId}/4"/>'"/>
                                    <label for="star4" title="4 star"></label>
                                    <input type="radio" id="star3" name="rating" value="3"
                                           onclick="location.href='<spring:url value="/customer/orders/rate/${order.customerOrderId}/3"/>'"/>
                                    <label for="star3" title="3 star"></label>
                                    <input type="radio" id="star2" name="rating" value="2"
                                           onclick="location.href='<spring:url value="/customer/orders/rate/${order.customerOrderId}/2"/>'"/>
                                    <label for="star2" title="2 star"></label>
                                    <input type="radio" id="star1" name="rating" value="1"
                                           onclick="location.href='<spring:url value="/customer/orders/rate/${order.customerOrderId}/1"/>'"/>
                                    <label for="star1" title="1 star"></label>
                                </div>
                            </div>
                        <%--<a href="<spring:url value="#"/>" class="btn btn-success">1</a>--%>
                        </td>
                    </c:if>
                    <c:if test="${!order.status.equals(\"Delivered\") && order.rating == 0}">
                        <td><a>-</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
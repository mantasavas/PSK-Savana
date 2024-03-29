<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />

<%--TODO: Show payment method--%>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Order</h1>

            <p class="lead">Order confirmation</p>
        </div>

        <div class="container">

            <div class="row">

                <form:form modelAttribute="order" class="form-horizontal">

                    <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">

                        <div class="txt-center">
                            <h1>Receipt</h1>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <address>
                                    <strong>Shipping Address</strong><br/>
                                        ${order.address.streetName}
                                    <br/>
                                        ${order.address.city}, ${order.address.state}
                                    <br/>
                                        ${order.address.country}, ${order.address.zipCode}
                                </address>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                <p>Shipping Date: <fmt:formatDate type="date" value="${now}"/></p>
                            </div>
                        </div>

                        <div class="row">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <td>Product</td>
                                    <td>#</td>
                                    <td class="text-center">Price</td>
                                    <td class="text-center">Total</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="cartItem" items="${order.cart.cartItems}">
                                    <fmt:parseDate value="${cartItem.product.productDiscountExpirationDatetime}" var="discountExpire" pattern="yyyy-MM-dd HH:mm:ss" />
                                    <tr>
                                        <td class="col-md-9"><em>${cartItem.product.productName}</em></td>
                                        <td class="col-md-1" style="text-align: center">${cartItem.quantity}</td>
                                        <c:if test="${discountExpire > now}">
                                            <td class="col-md-2" style="text-align: center">
                                                <a>$${cartItem.product.actualPrice}</a>
                                                <c:if test="${cartItem.product.productDiscountPercentage > 0}">
                                                    <a style="text-decoration: line-through;">$${cartItem.product.productPrice}</a>
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <c:if test="${discountExpire <= now}">
                                            <td class="col-md-2" style="text-align: center">$${cartItem.product.productPrice}</td>
                                        </c:if>
                                        <td class="col-md-2" style="text-align: center">$${cartItem.totalPrice}</td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td class="text-right">
                                        <h4><strong>Grand Total:</strong></h4>
                                    </td>
                                    <td class="text-center text-danger">
                                        <h4><strong>$${order.cart.grandTotal}</strong></h4>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>


                        <input type="hidden" name="_flowExecutionKey" />

                        <br/><br/>

                        <input type="submit" class="btn btn-primary" value="Submit Order" name="_eventId_orderConfirmed" />
                        <input type="submit" class="btn btn-secondary" value="Back" name="_eventId_backToCollectShippingDetail"/>
                        <input type="submit" class="btn btn-secondary" value="Cancel" name="_eventId_cancel"/>

                    </div>
                </form:form>
            </div>
        </div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
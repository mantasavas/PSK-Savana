<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer</h1>


            <p class="lead">Customer Details:</p>
        </div>

        <form:form modelAttribute="order" class="form-horizontal">

            <h3>Payment method</h3>

            <div class="form-group">
                <label for="holderName">Card owner name</label>
                <form:input path="card.name" id="holderName" class="form-Control" required="true"/>
            </div>

            <div class="form-group">
                <label for="cardNumber">Card number</label>
                <form:input path="card.number" id="cardNumber" class="form-Control" required="true"/>
            </div>

            <div class="form-group">
                <label for="expYear">Expiration year</label>
                <form:input path="card.expYear" id="expYear" class="form-Control" required="true"/>
            </div>

            <div class="form-group">
                <label for="expMonth">Expiration month</label>
                <form:input path="card.expMonth" id="expMonth" class="form-Control" required="true"/>
            </div>

            <div class="form-group">
                <label for="cvv">CVV</label>
                <form:input path="card.cvv" id="cvv" class="form-Control" required="true"/>
            </div>

            <input type="hidden" name="_flowExecutionKey" />

            <br/><br/>

            <button class="btn btn-default" name="_eventId_backToShippingDetail">Back</button>

            <input type="submit" value="Next" class="btn btn-primary" name="_eventId_paymentInfoCollected" />

            <button class="btn btn-default" name="_eventId_cancel">Cancel</button>

        </form:form>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


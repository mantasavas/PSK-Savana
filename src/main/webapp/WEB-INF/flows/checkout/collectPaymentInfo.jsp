<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer</h1>


            <p class="lead">Customer Details:</p>
        </div>

        <form:form modelAttribute="order" class="form-horizontal" autocomplete="on">

            <h3>Payment method</h3>

            <div class="form-group">
                <label for="holderName">Card owner name</label>
                <form:input path="card.name" id="holderName" class="form-Control" required="true" autocomplete='cc-name'/>
            </div>

            <div class="form-group">
                <label for="cardNumber">Card number</label>
                <form:input path="card.number" id="cardNumber" class="form-Control" required="true" autocomplete='cc-number'/>
            </div>

            <div class="form-group">
                <label for="expYear">Expiration year</label>
                <form:input path="card.expYear" id="expYear" class="form-Control" required="true" autocomplete='cc-exp-year'/>
            </div>

            <div class="form-group">
                <label for="expMonth">Expiration month</label>
                <form:input path="card.expMonth" id="expMonth" class="form-Control" required="true" autocomplete='cc-exp-month'/>
            </div>

            <div class="form-group">
                <label for="cvv">CVV</label>
                <form:input path="card.cvv" id="cvv" class="form-Control" required="true" autocomplete='cc-csc'/>
            </div>

            <input type="hidden" name="_flowExecutionKey" />

            <br/><br/>

            <input type="submit" class="btn btn-primary" value="Next" name="_eventId_paymentInfoCollected" />
            <input type="submit" class="btn btn-secondary" value="Back" name="_eventId_backToShippingDetail"/>
            <input type="submit" class="btn btn-secondary" value="Cancel" name="_eventId_cancel"/>
        </form:form>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


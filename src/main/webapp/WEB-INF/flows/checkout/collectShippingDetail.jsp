<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer</h1>


            <p class="lead">Customer Details:</p>
        </div>

        <form:form modelAttribute="order" class="form-horizontal" autocomplete="on">

            <h3>Shipping Address:</h3>

            <div class="form-group">
                <label for="shippingStreet">Street Name</label>
                <form:input path="address.streetName" id="shippingStreet" class="form-Control" autocomplete='address-line1' />
            </div>

            <div class="form-group">
                <label for="shippingApartmentNumber">Apartment Number</label>
                <form:input path="address.apartmentNumber" id="shippingApartmentNumber" class="form-Control" />
            </div>

            <div class="form-group">
                <label for="shippingCity">City</label>
                <form:input path="address.city" id="shippingCity" class="form-Control" autocomplete='address-level2' />
            </div>

            <div class="form-group">
                <label for="shippingState">State</label>
                <form:input path="address.state" id="shippingState" class="form-Control" autocomplete='address-level1' />
            </div>

            <div class="form-group">
                <label for="shippingCountry">Country</label>
                <form:input path="address.country" id="shippingCountry" class="form-Control" autocomplete='country-name'/>
            </div>

            <div class="form-group">
                <label for="shippingZip">Zipcode</label>
                <form:input path="address.zipCode" id="shippingZip" class="form-Control" autocomplete='postal-code' />
            </div>

            <input type="hidden" name="_flowExecutionKey" />

            <br/><br/>

            <input type="submit" class="btn btn-primary" value="Next" name="_eventId_shippingDetailCollected" />
            <input type="submit" class="btn btn-secondary" value="Back" name="_eventId_backToCollectCustomerInfo"/>
            <input type="submit" class="btn btn-secondary" value="Cancel" name="_eventId_cancel"/>

        </form:form>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
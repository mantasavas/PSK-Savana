<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Edit account information</h1>

            <p class="lead">Edit the fields that you want to update:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/customer/edit"
                   method="post" modelAttribute="customer">

        <h3>Basic Info:</h3>

        <div class="form-group">
            <label for="id"></label>
            <form:input  type="hidden" path="customerId" id="id" class="form-Control"  />
        </div>

        <div class="form-group">
            <label for="name">Name</label>
            <form:errors path="customerName" cssStyle="color: red" />
            <form:input path="customerName" id="name" class="form-Control"  />
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <span style="color: red">${emailMsg}</span>
            <form:errors path="customerEmail" cssStyle="color: red" />
            <form:input path="customerEmail" id="email" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <form:input path="customerPhone" id="phone" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:errors path="password" cssStyle="color: red" />
            <form:password path="password" id="password" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="passwordRepeat">Password (repeat password)</label>
            <span style="color: red">${pswRepeatMsg}</span>
            <form:password path="passwordRepeat" id="passwordRepeat" class="form-Control" />
        </div>

        <br/>

        <h3>Shipping Address:</h3>

        <div class="form-group">
            <label for="shippingStreet">Street</label>
            <form:input path="address.streetName" id="shippingStreet" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="shippingApartmentNumber">Apartment</label>
            <form:input path="address.apartmentNumber" id="shippingApartmentNumber" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="shippingCity">City</label>
            <form:input path="address.city" id="shippingCity" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="shippingState">State</label>
            <form:input path="address.state" id="shippingState" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="shippingCountry">Country</label>
            <form:input path="address.country" id="shippingCountry" class="form-Control" />
        </div>

        <div class="form-group">
            <label for="shippingZip">ZIP code</label>
            <form:input path="address.zipCode" id="shippingZip" class="form-Control" />
        </div>

        <br/>

        <h3>Payment method</h3>

        <div class="form-group">
            <label for="holderName">Card owner name</label>
            <form:input path="card.name" id="holderName" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="cardNumber">Card number</label>
            <form:input path="card.number" id="cardNumber" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="expYear">Expiration year</label>
            <form:input path="card.expYear" id="expYear" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="expMonth">Expiration month</label>
            <form:input path="card.expMonth" id="expMonth" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="cvv">CVV</label>
            <form:input path="card.cvv" id="cvv" class="form-Control"/>
        </div>

        <br/><br/>

        <input type="submit" value="Save changes" class="btn btn-primary">
        <a href="<c:url value="/" />" class="btn btn-default">Cancel</a>

        </form:form>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
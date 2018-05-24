<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer</h1>


            <p class="lead">Customer Details:</p>
        </div>

        <form:form modelAttribute="order" class="form-horizontal">

            <h3>Basic Info:</h3>

            <div class="form-group">
                <label for="name">Name</label>
                <form:input path="customer.customerName" id="name" class="form-Control" />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="customer.customerEmail" id="email" class="form-Control" />
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <form:input path="customer.customerPhone" id="phone" class="form-Control" />
            </div>

            <br/>

            <input type="hidden" name="_flowExecutionKey" />

            <br/><br/>

            <input type="submit" class="btn btn-primary" value="Next" name="_eventId_customerInfoCollected" />
            <input type="submit" class="btn btn-secondary" value="Cancel" name="_eventId_cancel"/>

    </form:form>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
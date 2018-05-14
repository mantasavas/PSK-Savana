<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Write feedback</h1>
            <p class="lead">Give your honest feedback about the order</p>
        </div>

        <form:form action="/customer/orders/${order.customerOrderId}/feedback?${_csrf.parameterName}=${_csrf.token}"
                   method="post" modelAttribute="order" enctype="multipart/form-data">

        <div class="form-group">
            <label for="feedback">Feedback (max 255 symbols)</label>
            <form:errors path="feedback" cssStyle="color: red" />
            <form:input path="feedback" id="feedback" class="form-Control"/>
        </div>

        <br>
        <br>
        <input type="submit" value="Submit" class="btn btn-primary">
        <a href="<c:url value="/customer/orders"/>" class="btn btn-secondary">Cancel</a>

        </form:form>

<%@ include file="common/footer.jsp"%>
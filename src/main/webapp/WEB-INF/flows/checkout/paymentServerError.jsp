<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Payment failed :(</h1>
                    <p>Something went wrong on our side. Try again later or contact support.</p>
                </div>
            </div>
        </section>

        <form:form>
            <button class="btn btn-default" name="_eventId_backToCollectPaymentInfo">Back</button>
            <a href="<spring:url value="/product/productList?searchCondition=\"\"" />" class="btn btn-primary">Products</a>
        </form:form>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


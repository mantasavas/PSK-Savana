<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

        <section class="container">
            <p><a href="<spring:url value="/product/productList?searchCondition=\"\"" />" class="btn btn-primary">Products</a></p>
        </section>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


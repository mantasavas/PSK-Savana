<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Payment unsuccessfull! Check if your card info is correct.</h1>
                </div>
            </div>
        </section>

        <section class="container">
            <p><a href="<spring:url value="/product/productList?searchCondition=\"\"" />" class="btn btn-primary">Products</a></p>
        </section>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


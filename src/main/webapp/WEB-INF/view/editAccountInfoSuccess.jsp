<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Information updated successfully!</h1>
                </div>
            </div>
        </section>

        <section class="container">
            <p><a href="<spring:url value="/product/productList?searchCondition=\"\"" />" class="btn btn-primary">Products</a></p>
        </section>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
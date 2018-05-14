<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Thank you!</h1>

                    <p>Your order will be shipped as soon as possible</p>
                </div>
            </div>
        </section>

        <section class="container">
            <p><a href="<spring:url value="/" />" class="btn btn-primary">Home</a></p>
        </section>

        <%@ include file="/WEB-INF/view/common/footer.jsp" %>

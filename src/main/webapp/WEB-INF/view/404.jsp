<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Page not found :(</h1>
                    <h3>${requestScope['javax.servlet.error.status_code']} ${requestScope['javax.servlet.error.message']}</h3>
                </div>
            </div>
        </section>

        <section class="container">
            <p><a href="<spring:url value="/" />" class="btn btn-primary">Return home</a></p>
        </section>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


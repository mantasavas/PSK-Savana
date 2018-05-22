<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>${displayMessage}</h1>
                </div>
            </div>
        </section>

        <section class="container">
            <p><a href="<spring:url value="/" />" class="btn btn-primary">Return home</a></p>
        </section>

         <!--Failed URL: ${url}
             Exception:  ${exception.message}
            <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
            </c:forEach>
          -->
    </div>
</div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>


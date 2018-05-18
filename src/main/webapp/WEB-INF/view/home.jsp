<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<main role="main">

    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">

            <c:forEach items="${categories}" var="categoryName">
                <div class="col-lg-4">
                    <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=${categoryName}" />" role="button">
                        <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="${categoryName} Image" width="140" height="140">
                    </a>
                    <h2>${categoryName}:</h2>
                    <c:if test="${categoryName.equals(\"Other\")}">
                        <p>Check out other stuff!</p>
                    </c:if>
                    <c:if test="${!categoryName.equals(\"Other\")}">
                        <p>Check out these ${categoryName.toLowerCase()}!</p>
                    </c:if>
                </div>
            </c:forEach>

        </div>

<%@ include file="common/footer.jsp"%>
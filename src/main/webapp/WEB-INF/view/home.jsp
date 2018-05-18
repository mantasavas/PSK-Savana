<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<main role="main">

    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">

            <c:forEach items="${categories}" var="category">
                <div class="col-lg-4">
                    <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=${category.productCategoryName}" />" role="button">
                        <img class="img-circle"
                             src="<c:url value="/resources/images/${category.productCategoryName}${category.productCategoryId}.png"/>"
                             onError="this.src = '/resources/images/default.png'"
                             alt="${category.productCategoryName} Image"
                             width="140" height="140">
                    </a>
                    <h2>${category.productCategoryName}:</h2>
                    <c:if test="${category.productCategoryName.equals(\"Other\")}">
                        <p>Check out other stuff!</p>
                    </c:if>
                    <c:if test="${!category.productCategoryName.equals(\"Other\")}">
                        <p>Check out these ${category.productCategoryName.toLowerCase()}!</p>
                    </c:if>
                </div>
            </c:forEach>

        </div>

<%@ include file="common/footer.jsp"%>
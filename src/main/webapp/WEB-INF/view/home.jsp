<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<main role="main">

    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=keyboard" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 1 Image" width="140" height="140">
                </a>
                <h2>Keyboards:</h2>
                <p>Check out these keyboards!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=mouse" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 2 Image" width="140" height="140">
                </a>
                <h2>Mouses:</h2>
                <p>Check out these mouses!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=monitor" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 3 Image" width="140" height="140">
                </a>
                <h2>Monitors:</h2>
                <p>Check out these monitors!</p>
            </div>

        </div>

        <!-- Three columns of text below the carousel -->
        <div class="row">

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=headphones" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 4 Image" width="140" height="140">
                </a>
                <h2>Headphones:</h2>
                <p>Check out these headphones!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=microphone" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 5 Image" width="140" height="140">
                </a>
                <h2>Microphones:</h2>
                <p>Check out these microphones!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=router" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 6 Image" width="140" height="140">
                </a>
                <h2>Routers:</h2>
                <p>Check out these routers!</p>
            </div>

        </div>

        <!-- Three columns of text below the carousel -->
        <div class="row">

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=printer" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 7 Image" width="140" height="140">
                </a>
                <h2>Printers:</h2>
                <p>Check out these printers!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=projector" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 8 Image" width="140" height="140">
                </a>
                <h2>Projectors:</h2>
                <p>Check out these projectors!</p>
            </div>

            <div class="col-lg-4">
                <a class="btn btn-default" href="<c:url value="/product/productList?searchCondition=other" />" role="button">
                    <img class="img-circle" src="<c:url value="/resources/images/default.png"/>" alt="Category 9 Image" width="140" height="140">
                </a>
                <h2>Other:</h2>
                <p>Check out other stuff!</p>
            </div>

        </div>

<%@ include file="common/footer.jsp"%>
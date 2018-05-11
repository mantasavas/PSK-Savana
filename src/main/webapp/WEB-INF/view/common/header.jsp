<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <%-- add csrf token --%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/logo.ico" />">

    <title>E-commerce</title>

    <%--<!-- Angular JS -->--%>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
    <!-- DataTables -->
    <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
    <%--Main CSS--%>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <%--DataTables CSS--%>
    <link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" rel="stylesheet">
    <%-- Scroll to the top CSS--%>
    <link href="<c:url value="/resources/css/scroller.css" />" rel="stylesheet">
    <!-- font awesome (icons) -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
</head>

<!-- NAVBAR
================================================== -->
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <img width="35" height="35" style="margin-right: 25px" src="<c:url value="/resources/images/logo.ico" />">
        <a class="navbar-brand" href="/">E-commerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/product/products"/>">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/about"/>">About</a>
                </li>
                
                <c:if test="${pageContext.request.userPrincipal.name == 'admin'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Resources</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="/admin/generateProductExcel/products/"/>">Export XLS</a>
                            <a class="dropdown-item" href="<c:url value="/admin/importProductExcel/excelFile/"/>">Import XLS</a>
                        </div>
                    </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}">

                    <c:if test="${pageContext.request.userPrincipal.name == 'admin'}">
                        <li><a class="nav-link" href="<c:url value="/admin" />">Admin panel</a></li>
                    </c:if>

                    <c:if test="${pageContext.request.userPrincipal.name != 'admin'}">
                        <li><a class="nav-link" href="<c:url value="/customer/cart" />">Cart</a></li>
                        <li><a class="nav-link" href="<c:url value="/customer/orders" />">Orders</a></li>
                        <li><a href="<c:url value="/customer/edit"/>" class="nav-link">${pageContext.request.userPrincipal.name}</a></li>
                    </c:if>

                    <li class="nav-item">
                        <a class="nav-link" href="javascript:{}" onclick="document.getElementById('my_form').submit();">Logout</a>
                    </li>

                    <form:form action="${pageContext.request.contextPath}/logout" id="my_form" method="POST">
                    </form:form>

                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a class="nav-link" href="<c:url value="/login" />">Login</a></li>
                    <li><a class="nav-link" href="<c:url value="/register" />">Register</a></li>
                </c:if>
            </ul>
            <%--<form class="form-inline mt-2 mt-md-0">--%>
                <%--<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">--%>
                <%--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
            <%--</form>--%>
        </div>
    </nav>
</header>

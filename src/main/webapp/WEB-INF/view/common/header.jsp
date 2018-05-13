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

    <script src="<c:url value="/resources/js/importExportController.js" /> "></script>
</head>

<!-- NAVBAR
================================================== -->
<body ng-app="importExportApp" >
<header>

    <script>
        $(document).ready(function(){
            var modalConfirm = function(callback) {

                // For confirmation if user is sure to download file!
                $("#btn-confirm").on("click", function () {
                    $("#my-modal").modal('show');
                });

                $("#modal-btn-yes").on("click", function () {
                    callback(true);
                    $("#my-modal").modal('hide');
                });

                $("#modal-btn-no").on("click", function () {
                    callback(false);
                    $("#my-modal").modal('hide');
                });
            };

            modalConfirm(function(confirm){
                if(confirm){
                    console.log("Inside download thread start!!! Run once and wait");
                    angular.element(document.getElementById('importExcelis')).scope().downloadExcelFile();
                    doAsync();
                    // If administrator confirmed!
                    //angular.element(document.getElementById('myCtrl')).scope().$apply("submit()")
                    //window.location = "/admin/generateProductExcel/products/?type=xls";
                }else{
                    // If administrator canceled! Execute logic here...
                }
            });

            var modalNotification = function(callback) {
                // For download notification, that excel document is ready

                $("#download-btn-yes").on("click", function () {
                    callback(true);

                    $("#download-modal").modal('hide');
                });

                $("#download-btn-no").on("click", function () {
                    callback(false);
                    $("#download-modal").modal('hide');
                });
            };

            modalNotification(function(confirm){
                // If user now want to download file!
                if(confirm){
                    setCookie("showDownloadNoti", "false", 2);
                }else{
                    // If administrator want later download! Execute logic here...
                    setCookie("showDownloadNoti", "false", 2);
                }
            });

        });

        $(document).ready(function(){
            console.log("this should get printed every time! " + getCookie("showDownloadNoti"));
            if(getCookie("showDownloadNoti") == "true")
            {
                console.log("calling dialog lkang");
                $("#download-modal").modal('show');
            }

            if(getCookie("finsihedFileDownload") == "false")
            {
                doAsync();
            }
        });

        function timeoutPromise (time) {
            return new Promise(function (resolve) {
                setTimeout(function () {
                    resolve(Date.now());
                }, time)
            })
        }

        function doSomethingAsync () {
            return timeoutPromise(1000);
        }

        async function doAsync () {
            setCookie("finsihedFileDownload", "false", 2);
            var start = Date.now(), time;
            while(true) {
                time = await
                doSomethingAsync();
                console.log("inside do asyc " + getCookie("waitingExcel"));

                angular.element(document.getElementById('importExcelis')).scope().checkAvailability();
                readyToDownload = angular.element(document.getElementById('importExcelis')).scope().returnedData;
                console.log("readyTODownonload " + readyToDownload)
                if (readyToDownload == "true") {
                    $("#download-modal").modal('show');
                    setCookie("showDownloadNoti", "true", 2);
                    setCookie("finsihedFileDownload", "true", 2);
                    break;
                }
            }
        }
        function setCookie(name,value,days) {
            var expires = "";
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days*24*60*60*1000));
                expires = "; expires=" + date.toUTCString();
            }
            document.cookie = name + "=" + (value || "")  + expires + "; path=/";
        }

        function getCookie(name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for(var i=0;i < ca.length;i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1,c.length);
                if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
            }
            return null;
        }
        function eraseCookie(name) {
            document.cookie = name +'=; Max-Age=-99999999;';
        }

    </script>

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
                        <li><a class="nav-link" href="<c:url value="/admin" />">Admin</a></li>
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

    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exportConfirmation" aria-hidden="true" id="my-modal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Do you really want export all data?</h4>
                </div>
                <div class="modal-footer" ng-controller="importExportController" id="importExcelis">
                    <button type="button" class="btn btn-default" id="modal-btn-yes">Confirm</button>
                    <button type="button" class="btn btn-primary" id="modal-btn-no">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exportConfirmation" aria-hidden="true" id="download-modal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Your document is ready to download!</h4>
                </div>
                <div class="modal-footer" ng-controller="importExportController" id="importExcelis">
                    <button type="button" class="btn btn-default" id="download-btn-yes" onclick="document.getElementById('downloadExcel').submit();">Download</button>
                    <button type="button" class="btn btn-primary" id="download-btn-no">Later</button>
                </div>
            </div>
        </div>
    </div>
    <form action="/admin/generateProductExcel/products" method="GET" id="downloadExcel">
        <input type="hidden" name="type" value="xls" />
    </form>
</header>

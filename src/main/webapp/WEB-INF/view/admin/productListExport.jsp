<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/header.jsp"%>

<script>
    $(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,5,10,30,-1], [1,5,10,30,"All"]],
            "iDisplayLength": 5
        });

    });

    // Intercepting form submit action, and send asynchronously instead
    $(function () {

        function validateCheckBox() {
            checked = $("input[type=checkbox]:checked").length;

            if(!checked) {
                alert("You must select at least one document!");
                return false;
            }
            else{
                return true;
            }

        }

        var modalConfirm = function(callback) {

            // For confirmation if user is sure to download file!

            $("#modal-btn-yes").on("click", function () {
                callback(true);
                $("#my-modal").modal('hide');
            });

            $("#modal-btn-no").on("click", function () {
                callback(false);
                $("#my-modal").modal('hide');
            });
        };


        $('form').on('submit', function (e) {
            console.log("User requested form submition, getting confirmation!");

            // Preventing from sending synchronous request
            e.preventDefault();

            if(validateCheckBox()) {
                $.ajax({
                    type: 'get',
                    url: '/admin/generateProductExcel/products/start',
                    data: $('form').serialize(),
                    beforeSend: function () {
                        // Present confirmation window
                        //$("#my-modal").modal('show');
                        //return modalConfirm;
                        return confirm("Are you sure wanna export selected products?");
                    },
                    success: function () {
                        console.log("Successfully send post request to start asynchronously extract excel file!");
                        console.log("Starting asyn get call to check if document is available to server...");
                        doAsync();
                    }
                });
            }
        });
    });


    modalConfirm(function(confirm){
        if(confirm){
            angular.element(document.getElementById('importExcelis')).scope().downloadExcelFile();
            doAsync();
            // If administrator confirmed!
            //angular.element(document.getElementById('myCtrl')).scope().$apply("submit()")
            //window.location = "/admin/generateProductExcel/products/?type=xls";
        }else{
            // If administrator canceled! Execute logic here...
        }
    });

</script>

<form:form>
    <div class="container-wrapper mt-4">
        <div class="container">
            <div class="page-header">
                <h1>Products to export</h1>
                <p class="lead">View all products that would be exported to Excel file!</p>
                <!--<p><button type="button" class="btn btn-warning" id="btn-confirm" >Download Excel</button></p>-->
                <p><button name="submit" type="submit" class="btn btn-warning">Download Excel</button></p>
            </div>

            <input type="hidden" name="varname" value="12545">

            <table class="table table-striped table-hover">
                <thead>
                    <tr class="bg-success">
                        <th>Export</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Condition</th>
                        <th>Description</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <td><div class="checkbox">
                            <label><input type="checkbox" name="selected" value="${product.productId}"></label>
                        </div></td>
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td>${product.productCategory}</td>
                        <td>${product.productCondition}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.productPrice}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exportConfirmation" aria-hidden="true" id="my-modal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">Do you really want export all data?</h4>
                </div>
                <div class="modal-footer" ng-controller ="importExportController" id="importExcelis">
                    <button type="button" class="btn btn-default" id="modal-btn-yes">Confirm</button>
                    <button type="button" class="btn btn-primary" id="modal-btn-no">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</form:form>

<%@ include file="../common/footer.jsp"%>
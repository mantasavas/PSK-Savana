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


    $(document).ready(function(){
        var modalConfirm = function(callback) {

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
        }

        modalConfirm(function(confirm){
            if(confirm){
                // If administrator confirmed!
                window.location = "/admin/generateProductExcel/products/?type=xls";
            }else{
                // If administrator canceled! Execute logic here...
            }
        });
    });
</script>



    <div class="container-wrapper mt-4">
        <div class="container">
            <div class="page-header">
                <h1>Products to export</h1>
                <p class="lead">View all products that would be exported to Excel file!</p>
                <spring:url value="/admin/generateReport/report/?type=xls" var="xlsURL"/>
                <p><button type="button" class="btn btn-warning" id="btn-confirm">Download Excel</button></p>
            </div>

            <table class="table table-striped table-hover">
                <thead>
                <tr class="bg-success">
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
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td>${product.productCategory}</td>
                        <td>${product.productCondition}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.productPrice}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exportConfirmation" aria-hidden="true" id="my-modal">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Do you really want export all data?</h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" id="modal-btn-yes">Confirm</button>
                            <button type="button" class="btn btn-primary" id="modal-btn-no">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>


<%@ include file="../common/footer.jsp"%>
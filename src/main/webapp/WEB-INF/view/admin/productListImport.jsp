<%--
  Created by IntelliJ IDEA.
  User: mantas
  Date: 18.5.11
  Time: 15.13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../common/header.jsp"%>


<script>
    $(document).ready(function(){
        var modalConfirm = function(callback) {

            $("#btn-confirm-import").on("click", function () {
                $("#import-excel-file").modal('show');
            });

            $("#modal-btn-yes-import").on("click", function () {
                callback(true);
                $("#import-excel-file").modal('hide');
            });

            $("#modal-btn-no-import").on("click", function () {
                callback(false);
                $("#import-excel-file").modal('hide');
            });
        }

        modalConfirm(function(confirm){
            if(confirm){
                // If administrator confirmed!
                document.import.submit();
            }else{
                // If administrator canceled! Execute logic here...
            }
        });
    });
</script>



<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Import product data from Excel file</h1>
            <h3> Choose file </h3>
            <c:url value="/admin/importProductExcel/excelFile" var="uploadFileUrl" />
            <form name="import" method="post" enctype="multipart/form-data" action="${uploadFileUrl}?${_csrf.parameterName}=${_csrf.token}">
                <p><input style="color: black" type="file" name="file" accept=".xls,.xlsx" /></p>
                <p class="file-submit-button"><button type="button" class="btn btn-warning" id="btn-confirm-import">Import Excel</button></p>
            </form>
        </div>

        <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exportConfirmation" aria-hidden="true" id="import-excel-file">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Do you really want to import data?</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="modal-btn-yes-import">Confirm</button>
                        <button type="button" class="btn btn-primary" id="modal-btn-no-import">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp"%>
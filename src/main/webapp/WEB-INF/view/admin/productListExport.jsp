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


</script>

    <div class="container-wrapper mt-4">
        <div class="container">
            <div class="page-header">
                <h1>Products to export</h1>
                <p class="lead">View all products that would be exported to Excel file!</p>
                <spring:url value="/admin/generateReport/report/?type=xls" var="xlsURL"/>
                <p><button type="button" class="btn btn-warning" id="btn-confirm">Download Excel</button></p>
                <p> {{checkValue}} </p>
                <a ng-href="{{ fileUrl }}" download="file.txt">download</a>
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
        </div>
    </div>
<%@ include file="../common/footer.jsp"%>
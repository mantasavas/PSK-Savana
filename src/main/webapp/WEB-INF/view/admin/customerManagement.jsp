<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<script>
    $(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,5,10,30,-1], [1,5,10,30,"All"]],
            "iDisplayLength": 5
        });
    });
</script>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer Management</h1>

            <p class="lead">This is the customer management page!</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Enabled</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.customerEmail}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.customerPhone}</td>
                    <td>${customer.enabled}</td>
                    <td>
                        <c:if test="${customer.enabled == false}">
                            <a href="<spring:url value="/admin/customers/enable/${customer.customerId}"/>" class="btn btn-success">Enable</a>
                        </c:if>
                        <c:if test="${customer.enabled == true}">
                            <a href="<spring:url value="/admin/customers/disable/${customer.customerId}"/>" class="btn btn-danger">Disable</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
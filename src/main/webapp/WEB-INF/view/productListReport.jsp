<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="common/header.jsp"%>

    <div class="container-wrapper mt-4">
        <div class="container">
            <div class="page-header">
                <h1>Products to export</h1>
                <p class="lead">View all products that would be exported to Excel file!</p>
                <spring:url value="/admin/generateReport/report/?type=xls" var="xlsURL"/>
                <p><a href="${xlsURL}">Download Excel</a></p>
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
                <c:forEach items="${productList}" var="user">
                    <tr>
                        <td>${user.productId}</td>
                        <td>${user.productName}</td>
                        <td>${user.productCategory}</td>
                        <td>${user.productCondition}</td>
                        <td>${user.productDescription}</td>
                        <td>$${user.productPrice}</td>
                    </tr>
                </c:forEach>
            </table>


<%@ include file="common/footer.jsp"%>
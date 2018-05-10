<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="common/header.jsp"%>

    <table>
        <thead>
            <tr>
                <td>ID<td>
                <td>Name<td>
                <td>Category</td>
                <td>Description</td>
            <tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.productId}</td>
                    <td>${user.productName}</td>
                    <td>${user.productCategory}</td>
                    <td>${user.productDescription}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <spring:url value="/generateReport/report/?type=xls" var="xlsURL"/>
    <a href="${xlsURL}">Download Excel</a>

<%@ include file="common/footer.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jsp"%>

<div class="container-wrapper mt-4">
    <div class="container">
        <div class="page-header">
            <h1>Admin panel</h1>
            <p class="lead">This is only for administrator!</p>
        </div>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Welcome: ${pageContext.request.userPrincipal.name} |
                <%--<a href="<c:url value="/logout"/>">Logout</a>--%>
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="submit" value="Logout" class="btn-link text-info"/>
                </form:form>
            </h2>
        </c:if>

        <h3>
            <a href="<c:url value="/admin/productInventory"/>" >Product Inventory</a>
        </h3>

        <p>Here you can view, check and modify product inventory</p>

<%@ include file="common/footer.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Feedback</h1>
                    <h3>${feedback}</h3>
                </div>
            </div>
        </section>

        <section class="container">
            <c:if test="${role != null}">
                <c:if test="${role == 'admin'}">
                    <p><a href="<c:url value="/admin/orders" />" class="btn btn-default">Order Management</a></p>
                </c:if>
                <c:if test="${role == 'customer'}">
                    <p><a href="<c:url value="/customer/orders" />" class="btn btn-default">Orders</a></p>
                </c:if>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <p><a href="<c:url value="/" />" class="btn btn-default">Home</a></p>
            </c:if>
        </section>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
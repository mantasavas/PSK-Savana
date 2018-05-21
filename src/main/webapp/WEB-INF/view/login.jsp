<%@ include file="common/header.jsp"%>

<div class="container-wrapper">
    <div class="container">
        <div id="login-box">

            <h2>Login with email and password</h2>

            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="error" style="color: red">${error}</div>
            </c:if>

            <c:if test="${not empty disabled}">
                <div class="disabled" style="color: red">${disabled}</div>
            </c:if>

            <form name="loginForm" action="<c:url value="/login"/>" method="post">

                <div class="form-group">
                    <label for="username">Email:</label>
                    <input type="text" id="username" name="username" class="form-control" autocomplete="username"/>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" autocomplete="current-password"/>
                </div>

                <div class="form-group">
                    <label for="remember">Remember Me?</label>
                    <input type="checkbox" id="remember" name="remember-me"/>
                </div>

                <input type="submit" value="Login" class="btn btn-primary"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            </form>
        </div>
    </div>
</div>

<%@ include file="common/footer.jsp"%>

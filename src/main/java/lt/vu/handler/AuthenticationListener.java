package lt.vu.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationListener implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException ae) throws IOException {
        String errorMsg = ae.getLocalizedMessage();

        if (errorMsg.contains("disabled")) {
            response.sendRedirect("login?disabled=true");
            return;
        }

        response.sendRedirect("login?error=true");
    }
}
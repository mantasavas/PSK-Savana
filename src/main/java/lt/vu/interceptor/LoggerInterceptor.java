package lt.vu.interceptor;

import lt.vu.dao.api.LogDao;
import lt.vu.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private LogDao logDao;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            SecurityContextImpl securityContext = ((SecurityContextImpl) request
                    .getSession()
                    .getAttribute("SPRING_SECURITY_CONTEXT"));

            if (securityContext != null && handler instanceof HandlerMethod) {
                User user = (User) securityContext.getAuthentication().getPrincipal();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                String userName = user.getUsername();
                String userRole = user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                        .get(0);

				String methodName = ((HandlerMethod) handler).getMethod().getName();
				String className = ((HandlerMethod) handler).getBeanType().getName();

                Log log = new Log(timestamp, userName, userRole, className, methodName, "");
                logDao.addLog(log);
            }
        }
        catch (Exception ex) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Log log = new Log(timestamp, "", "", "", "",
					String.format("Logger error: %s", ex.getMessage()));
			logDao.addLog(log);
        }
        finally {
            return true;
        }
    }
}

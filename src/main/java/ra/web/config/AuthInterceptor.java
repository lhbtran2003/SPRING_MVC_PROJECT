package ra.web.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userLogin = request.getSession().getAttribute("userLogin");
        if (userLogin == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return false;
        }
        return true;
    }
}

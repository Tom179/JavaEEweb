package Filter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = { "/Login" })
public class AuthLoginFilter implements Filter  {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {//如果session中不存在
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("autologin".equals(cookie.getName())) {//查找autologin的cookie
                        System.out.println("用户设置自动登录");
                        request.setAttribute("username",cookie.getValue());
                        httpRequest.getRequestDispatcher("/success").forward(request, response);
                        return;
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code (if needed)
    }

    public void destroy() {
        // Cleanup code (if needed)
    }


}

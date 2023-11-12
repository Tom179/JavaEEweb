package Filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},//////
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 从初始化参数中获取编码
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 设置请求和响应的字符编码
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);

        // 继续处理请求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁过滤器
    }
}

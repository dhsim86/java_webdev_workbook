package Lesson04;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Dongho on 2017. 3. 8..
 */
public class CharacterEncodingFilter implements Filter {

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(
        ServletRequest request, ServletResponse response, FilterChain nextFilter)
        throws IOException, ServletException {

        request.setCharacterEncoding(filterConfig.getInitParameter("encoding"));
        nextFilter.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

package controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
public class WhiteListFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String userLogin = (String)((HttpServletRequest)request).getSession().getAttribute("userLogin");

        if (userLogin != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response)
                    .sendRedirect(((HttpServletRequest)request).getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}

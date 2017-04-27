package controllers.filters;

import models.POJO.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
public class WhiteListFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(WhiteListFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User)((HttpServletRequest)request).getSession().getAttribute("user");

        LOGGER.debug(user);
        if (user != null) {
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

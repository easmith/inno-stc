package Controllers.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
//@WebFilter("/UserFilter")
public class UserFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(UserFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.info("UserFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        LOGGER.info("Requested uri: " + uri);

        if (!uri.endsWith("login")) {
            if (session == null || session.getAttribute("userSession") == null) {
                LOGGER.info("Unauthorized access request");
                res.sendRedirect(req.getContextPath() + "/login");
            } else {
                LOGGER.info("Auth filter: " + session.getAttribute("userSession"));
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }


    public void destroy() {
        //close any resources here
    }

}
package Controllers.filters;


import Models.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
//@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AdminFilter.class);
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("UserFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        LOGGER.info("Requested uri: " + uri);

        if (session != null && session.getAttribute("userSession") != null) {
            if (session.getAttribute("userSession") instanceof UserSession
                && ((UserSession) session.getAttribute("userSession")).getAdmin()) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/user");
            }
        } else {
            LOGGER.info("Unauthorized access request");
            res.sendRedirect(req.getContextPath() + "/login");
        }

    }


    public void destroy() {
        //close any resources here
    }

}
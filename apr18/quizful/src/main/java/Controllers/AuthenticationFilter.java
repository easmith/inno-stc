package Controllers;


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
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
//        this.context.log("Requested Resource::" + uri);
        HttpSession session = req.getSession(false);
        LOGGER.info("Requested Resource::" + uri);

        if (session == null && !(uri.endsWith("login.jsp") || uri.endsWith("LoginServlet"))) {
//            this.context.log("Unauthorized access request");
            LOGGER.info("Unauthorized access request");
            res.sendRedirect("login.jsp");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }

    }


    public void destroy() {
        //close any resources here
    }

}
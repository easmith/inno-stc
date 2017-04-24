package controllers;

import org.apache.log4j.Logger;
import services.UserServiceImpl;
import services.UserServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 19.04.17.
 */
public class LoginController extends HttpServlet {
    private static UserServiceInterface userService = new UserServiceImpl();

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (userService.auth(login, pass) != null) {
            req.getSession().setAttribute("userLogin", login);
            LOGGER.debug("user:" + login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        } else {
//            resp.sendRedirect(req.getContextPath() + "/error");
        }

//        req.getRequestDispatcher()

    }
}

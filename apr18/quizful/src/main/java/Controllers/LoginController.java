package Controllers;

import Models.pojo.User;
import Services.UserService;
import Services.UserServiceInterface;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by eku on 20.04.17.
 */
//@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private static UserServiceInterface userService = new UserService();

    private final String mainPage = "/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menuItem", "login");

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        LOGGER.info("Requested Resource::" + uri);

        Boolean userIsAdmin = null;

        if (session != null) {
            userIsAdmin = (Boolean) session.getAttribute("userIsAdmin");
        }

        if (userIsAdmin != null) {
            if (userIsAdmin) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        } else {
            req.getRequestDispatcher(mainPage).forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.auth(login, password);
        if(user != null){
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", user.getLogin());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userIsAdmin", user.getAdmin());
            session.setMaxInactiveInterval(30*60);
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(30*60);
            resp.addCookie(cookie);
            if (user.getAdmin()) {
                resp.sendRedirect("admin");
            } else {
                resp.sendRedirect("user");
            }
        }else{
            req.setAttribute("menuItem", "login");
            req.setAttribute("error", "Неправильный логин или пароль");
            req.getRequestDispatcher(mainPage).forward(req, resp);
        }

    }

}
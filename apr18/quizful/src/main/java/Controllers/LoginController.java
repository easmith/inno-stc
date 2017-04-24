package Controllers;

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
    private final String userID = "admin";
    private final String password = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menuItem", "login");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        if(userID.equals(user) && password.equals(pwd)){
            HttpSession session = req.getSession();
            session.setAttribute("user", "easmith");
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30*60);
            resp.addCookie(userName);
            resp.sendRedirect("LoginSuccess.jsp");
        }else{
            req.setAttribute("menuItem", "login");
            req.setAttribute("error", "Неправильный логин или пароль");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

}
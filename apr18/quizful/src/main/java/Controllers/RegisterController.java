package Controllers;

import Models.dao.UserDao;
import Models.pojo.User;
import Services.UserService;
import Services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
//@WebServlet("/LoginController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    private static UserServiceInterface userService = new UserService();

    private final String mainPage = "/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menuItem", "register");
        req.getRequestDispatcher(mainPage).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("menuItem", "register");

        // get request parameters for userID and password
        String login = req.getParameter("login");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (password2 == null | !password2.equals(password1)) {
            req.setAttribute("error", "Пароли не совпадают");
        } else if (password2 != null && password2.equals(password1)) {
            String hashed = BCrypt.hashpw(password1, BCrypt.gensalt(12));
            req.setAttribute("error", hashed);

            User user = userService.auth("admin", "pass");

            req.setAttribute("error", user.getName());

//            if (BCrypt.checkpw(login, hashed))
//                req.setAttribute("error", "ok");
//            else
//                req.setAttribute("error", "Bad");

//            HttpSession session = req.getSession();
//            session.setAttribute("user", "easmith");
//            //setting session to expiry in 30 mins
//            session.setMaxInactiveInterval(30*60);
//            Cookie userName = new Cookie("user", user);
//            userName.setMaxAge(30*60);
//            resp.addCookie(userName);
//            resp.sendRedirect("LoginSuccess.jsp");
        } else {
            req.setAttribute("error", "Непредвиденная ошибка");
        }

        User user = userService.auth("admin", "pass");

        req.setAttribute("error", user.getName());

        LOGGER.info(user.getName());

        req.getRequestDispatcher(mainPage).forward(req, resp);
    }

}
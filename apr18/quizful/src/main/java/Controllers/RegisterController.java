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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        if (name == null || login == null) {
            req.setAttribute("error", "Имя и логин не могут быть пустыми");
        } else if (password2 == null && password2.length() < 5) {
            req.setAttribute("error", "Пароль слишком короткий");
        } else if (password2 == null | !password2.equals(password1)) {
            req.setAttribute("error", "Пароли не совпадают");
        } else if (password2 != null && password2.equals(password1)) {
            if(userService.existUser(login)) {
                req.setAttribute("error", "Пользователь с таким логином уже существует");
            } else {
                User user = new User(0, name, login, password1, "admin".equals(login));
                userService.addUser(user);
                req.setAttribute("error", "User id: " + user.getId());
            }
        } else {
            req.setAttribute("error", "Непредвиденная ошибка");
        }

        req.getRequestDispatcher(mainPage).forward(req, resp);
    }

}
package Servlets;

import Models.pojo.User;
import Services.UserServiceInterface;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 20.04.17.
 */
//@WebServlet("/LoginServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private final String mainPage = "/register.jsp";
    @Autowired
    private UserServiceInterface userService;// = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

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
        if ("".equals(name) || "".equals(login)) {
            req.setAttribute("error", "Имя и логин не могут быть пустыми");
        } else if (password2 != null && password2.length() < 5) {
            req.setAttribute("error", "Пароль слишком короткий");
        } else if (password2 == null | !password2.equals(password1)) {
            req.setAttribute("error", "Пароли не совпадают");
        } else if (password2 != null && password2.equals(password1)) {
            try {
                if (userService.findUserByLogin(login) != null) {
                    req.setAttribute("error", "Пользователь с таким логином уже существует");
                } else {
                    User user = new User(0, login, password1, name,
                            "admin".equals(login) ? "ROLE_ADMIN" : "ROLE_USER",
                            true
                        );
                    userService.addUser(user);
                    req.setAttribute("error", "User id: " + user.getId());
                }
            } catch (QuizInternalException e) {
                req.setAttribute("error", QuizInternalException.commonMessage);
            }
        } else {
            req.setAttribute("error", "Непредвиденная ошибка");
        }

        req.getRequestDispatcher(mainPage).forward(req, resp);
    }

}
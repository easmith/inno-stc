package Controllers;

import Models.UserSession;
import Models.pojo.User;
import Services.UserServiceInterface;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by eku on 27.04.17.
 */
@Controller
@SessionAttributes(types = UserSession.class)
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    public UserServiceInterface userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(Model model) {
        model.addAttribute("menuItem", "index");
        return "landing";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpSession session) throws Exception {
        try {
            UserSession userSession = null;
            if (session != null && (userSession = (UserSession) session.getAttribute("userSession")) != null) {
                return "redirect:/" + (userSession.getAdmin() ? "admin" : "user");
            }
        } catch (Exception e) {
            LOGGER.error(e);
            session.setAttribute("fatalError", QuizInternalException.commonMessage);
        }
        session.setAttribute("menuItem", "login");
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@RequestParam("login") String login,
                              @RequestParam("password") String password) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        User user = null;
        try {
            user = userService.auth(login, password);
        } catch (QuizInternalException e) {
            mav.addObject("fatalError", QuizInternalException.commonMessage);
            return mav;
        }

        if (user != null) {
            mav.addObject("userSession", new UserSession(user));
            if (user.getRole() == "ROLE_ADMIN") {
                mav.setViewName("redirect:/admin");
            } else {
                mav.setViewName("redirect:/user");
            }
        } else {
            mav.addObject("error", "Неправильный логин или пароль");
        }

        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus status) {

        ModelAndView mav = new ModelAndView();
        status.setComplete();
        mav.setViewName("redirect:/");

        return mav;
    }
}

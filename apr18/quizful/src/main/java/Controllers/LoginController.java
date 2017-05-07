package Controllers;

import Models.UserSession;
import Models.pojo.User;
import Services.UserServiceInterface;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    public String loginPage(HttpSession session) {
        UserSession userSession = null;
        try {
            if (session == null || (userSession = (UserSession) session.getAttribute("userSession")) == null) {
                Object principalObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principalObject instanceof org.springframework.security.core.userdetails.User) {
                    org.springframework.security.core.userdetails.User principal =
                            (org.springframework.security.core.userdetails.User) principalObject;
                    Models.pojo.User user = userService.findUserByLogin(principal.getUsername());
                    if (user == null) {
                        throw new QuizInternalException();
                    }
                    userSession = new UserSession(user);
                    session.setAttribute("userSession", userSession);
                }
            }
            if (userSession != null) {
                return "redirect:/" + (userSession.getAdmin() ? "admin" : "user");
            }
        } catch (Exception e) {
            LOGGER.error(e);
            session.setAttribute("fatalError", QuizInternalException.commonMessage);
        }
        session.setAttribute("menuItem", "login");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

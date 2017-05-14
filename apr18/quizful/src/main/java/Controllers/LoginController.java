package Controllers;

import Exceptions.QuizInternalException;
import Models.UserSession;
import Services.QuestionServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus status) {

        ModelAndView mav = new ModelAndView();
        status.setComplete();
        mav.setViewName("redirect:/");

        return mav;
    }
}

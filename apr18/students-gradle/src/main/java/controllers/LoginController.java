package controllers;

import models.POJO.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.UserServiceInterface;

/**
 * Created by eku on 27.04.17.
 */
@Controller
@SessionAttributes(types = User.class)
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private UserServiceInterface userService;// = new UserServiceImpl();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String sayHello() {
        return "WEB-INF/pages/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password) {
        ModelAndView mav = new ModelAndView();
        User user = null;
        if ((user = userService.auth(login, password)) != null) {
            //req.getSession().setAttribute("login", login);
            LOGGER.debug("login: " + login);
//            resp.sendRedirect(req.getContextPath() + "/home");
            mav.addObject("user", user);
            mav.setViewName("redirect:/home");
        } else {
            mav.setViewName("WEB-INF/pages/login");
        }
        return mav;
    }


}

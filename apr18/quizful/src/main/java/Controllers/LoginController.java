package Controllers;

import Services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by eku on 27.04.17.
 */
@Controller
public class LoginController {

    @Autowired
    public UserServiceInterface userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("menuItem", "login");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginCheck(@RequestParam("login") int login,
                                   @RequestParam("password") int password) {

        ModelAndView mav = new ModelAndView();

        mav.addAttribute("menuItem", "login");
        return "login";
    }
}

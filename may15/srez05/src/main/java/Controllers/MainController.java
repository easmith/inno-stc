package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by eku on 15.05.17.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {
        return "loginForm";

    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/only_for_user", method = RequestMethod.GET)
    public String forUser() {
        return "forUser";
    }

    @RequestMapping(value = "/only_for_admin", method = RequestMethod.GET)
    public String forAdmin() {
        return "forAdmin";
    }
}

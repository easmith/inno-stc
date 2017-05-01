package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.UserServiceInterface;

@Controller
@Scope("session")
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private UserServiceInterface userService;// = new UserServiceImpl();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeIndex() {
        return "WEB-INF/pages/home";
    }
}
package Controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eku on 30.04.17.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping()
    public String defaultMethod(){
        return "adminHome";
    }
}

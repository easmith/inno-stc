package Controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eku on 27.04.17.
 */

@Controller
public class IndexController {

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeIndex(Model model) {
        model.addAttribute("menuItem", "index");
        return "landing";
    }
}

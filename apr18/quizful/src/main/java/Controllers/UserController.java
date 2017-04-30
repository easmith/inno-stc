package Controllers;

import Models.UserSession;
import Services.CategoryServiceInterface;
import Services.ResultTaskService;
import Services.UserServiceInterface;
import exceptions.QuizInternalException;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by eku on 29.04.17.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(types = UserSession.class)
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    public CategoryServiceInterface categoryService;

    @Autowired
    public ResultTaskService resultTaskService;

    @GetMapping
    public String defaultMethod(Model model){
        try {
            model.addAttribute("categories", categoryService.getCategories());
        } catch (QuizInternalException e) {
            model.addAttribute("fatalError", QuizInternalException.commonMessage);
        }
        return "userHome";
    }

    @GetMapping(value="/start/{id}")
    public String startTest(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        LOGGER.info(id);
        try {
            categoryService.getCategoryById(id);
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return "redirect:/user";
        }
        // todo: create result
        // todo: create resultTasks

        return "redirect:/user/quiz/3";
    }

    @GetMapping(value="/quiz/{id}")
    public String quiz(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        LOGGER.info(id);
//        model.addAttribute("result", );

        try {
            model.addAttribute("resultTasks", resultTaskService.getResultTasksByResultId(1));
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return "redirect:/user";
        }

        return "quiz";
    }

}

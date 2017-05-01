package Controllers;

import Models.forms.QuizForm;
import Models.UserSession;
import Models.pojo.Category;
import Models.pojo.Result;
import Models.pojo.ResultTask;
import Services.CategoryServiceInterface;
import Services.ResultService;
import Services.ResultTaskService;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    public ResultService resultService;

    @GetMapping
    public String defaultMethod(Model model, HttpSession session){
        UserSession userSession = (UserSession) session.getAttribute("userSession");

        try {
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("results", resultService.getResultsByUserId(userSession.getId()));
        } catch (QuizInternalException e) {
            model.addAttribute("fatalError", QuizInternalException.commonMessage);
        }
        return "userHome";
    }

    @GetMapping(value="/start/{id}")
    public String startTest(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        LOGGER.info(id);
        Category category = null;
        Result result = null;
        try {
            category = categoryService.getCategoryById(id);
            if (category == null) {
                redirectAttributes.addFlashAttribute("fatalError", "Категории не существует");
                return "redirect:/user";
            }

            result = resultService.createResult(2, category.getId());
            resultTaskService.createResultTasksByResult(result);
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return "redirect:/user";
        }

        return "redirect:/user/quiz/" + result.getId();
    }

    @GetMapping(value="/quiz/{id}")
    public String quiz(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        LOGGER.info(id);
//        model.addAttribute("result", );

        try {
            List<ResultTask> resultTasks = resultTaskService.getResultTasksByResultId(id);
            if (resultTasks.size() == 0) {
                redirectAttributes.addFlashAttribute("fatalError", "Вопросы не найдены");
                return "redirect:/user";
            }
            model.addAttribute("resultTasks", resultTasks);
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return "redirect:/user";
        }

        return "quiz";
    }

    @PostMapping(value="/quiz/{id}")
    public ModelAndView quizSave(
            @PathVariable("id") int id, QuizForm quizForm, RedirectAttributes redirectAttributes) {

        try {
            Result result = resultService.getResultsById(id);
            if (result == null) {
                redirectAttributes.addFlashAttribute("fatalError", "Тест не найден");
                return new ModelAndView("redirect:/user");
            }
            resultService.updateResult(result);
            resultTaskService.saveAnswers(quizForm.getAnswers());
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return new ModelAndView("redirect:/user");
        }

        return new ModelAndView("redirect:/user");
    }

}

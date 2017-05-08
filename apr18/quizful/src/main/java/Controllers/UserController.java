package Controllers;

import Models.UserSession;
import Models.forms.QuizForm;
import Models.pojo.Category;
import Models.pojo.Question;
import Services.*;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public QuestionResultServiceInterface questionResultService;

    @Autowired
    public QuestionServiceInterface questionService;

    @Autowired
    public UserServiceInterface userService;

    @GetMapping
    public String defaultMethod(Model model, HttpSession session) {
        UserSession userSession = null;
        try {
            if (session == null || (userSession = (UserSession) session.getAttribute("userSession")) == null) {
                User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Models.pojo.User user = userService.findUserByLogin(principal.getUsername());
                if (user == null) {
                    throw new QuizInternalException();
                }
                userSession = new UserSession(user);
                model.addAttribute("userSession", userSession);
            }
            model.addAttribute("categories", categoryService.getCategories());
        } catch (Exception e) {
            LOGGER.error(e);
            model.addAttribute("fatalError", QuizInternalException.commonMessage);
        }
        return "userHome";
    }

    @GetMapping(value = "/quiz/{categoryId}")
    public String quiz(@PathVariable("categoryId") int categoryId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            Question question = questionService.getQuestionByCategoryId(category.getId());
            LOGGER.info("Question for category " + categoryId);
            if (question == null) {
                redirectAttributes.addFlashAttribute("fatalError", "Вопросы не найдены");
                return "redirect:/user";
            }
            model.addAttribute("category", category);
            model.addAttribute("question", question);
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            return "redirect:/user";
        }

        return "quiz";
    }

    @PostMapping(value = "/quiz/{id}")
    public ModelAndView quizSave(
            @PathVariable("id") int id, QuizForm quizForm, RedirectAttributes redirectAttributes) {

//        try {
//            Result result = resultService.getResultsById(id);
//            if (result == null) {
//                redirectAttributes.addFlashAttribute("fatalError", "Тест не найден");
//                return new ModelAndView("redirect:/user");
//            }
//            resultService.updateResult(result);
//            questionResultServiceInterface.saveAnswers(quizForm.getAnswers());
//        } catch (QuizInternalException e) {
//            LOGGER.error(e);
//            redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
//            return new ModelAndView("redirect:/user");
//        }

        return new ModelAndView("redirect:/user");
    }

}

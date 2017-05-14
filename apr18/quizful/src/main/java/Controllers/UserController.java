package Controllers;

import Models.UserSession;
import Models.forms.QuizForm;
import Models.pojo.Answer;
import Models.pojo.Category;
import Models.pojo.Question;
import Services.*;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public QuestionResultServiceInterface questionResultService;

    @Autowired
    public QuestionServiceInterface questionService;

    @Autowired
    public UserServiceInterface userService;

    @GetMapping
    public String defaultMethod(Model model, HttpSession session) {
        UserSession userSession = null;

        if (session != null) {
            LOGGER.info(session.getAttribute("wow"));
        }

        try {
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

    @RequestMapping(value = "/quiz/check/{questionId}", method = RequestMethod.POST)
    @ResponseBody
    public Object test(@PathVariable("questionId") int questionId, @RequestBody Object request, HttpSession session) {
        Question question = questionService.getById(questionId);

        List<Integer> correctAnswers = new ArrayList<>();
        for (Answer answer :
                question.getAnswers()) {
            if (answer.getCorrect()) {
                correctAnswers.add(answer.getId());
            }
        }
        //TODO: сохранить результат для пользователя
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        if (userSession != null) {
            LOGGER.info("save result");
//            questionResultService.createQuestionResult(question, userSession.getId(), "", true);
        }

        return new Object() {
            public int question = questionId;
            public List<Integer> answers = correctAnswers;
        };
    }

}

package Controllers;

import Models.forms.AddQuestionForm;
import Models.pojo.Answer;
import Models.pojo.Question;
import Services.AnswerServiceInterface;
import Services.QuestionServiceInterface;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by eku on 30.04.17.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    public QuestionServiceInterface questionService;

    @Autowired
    public AnswerServiceInterface answerService;

    @RequestMapping()
    public String defaultMethod() {
        return "adminHome";
    }

    @PostMapping("/question")
    public String addQuestion(AddQuestionForm addQuestionForm, Model model) {
        LOGGER.info(addQuestionForm);

        Question question = new Question(0,
                addQuestionForm.getQuestionType(),
                addQuestionForm.getQuestionText(),
                addQuestionForm.getCategoryId());
        try {
            questionService.addQuestion(question);

            for (Map.Entry<Integer, String> entry :
                    addQuestionForm.getAnswer().entrySet()) {
                answerService.addAnswer(new Answer(
                        0,
                        question.getId(),
                        entry.getValue(),
                        addQuestionForm.getAnswerIsCorrect().containsKey(entry.getKey())
                ));
            }
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            model.addAttribute("fatalError", QuizInternalException.commonMessage);
        }

        model.addAttribute("fatalError", "Вопрос добавлен");

        return "adminHome";
    }
}

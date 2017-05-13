package Controllers;

import Models.forms.AddQuestionForm;
import Models.pojo.Answer;
import Models.pojo.Question;
import Services.AnswerServiceInterface;
import Services.QuestionServiceInterface;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String addQuestion(@Valid AddQuestionForm addQuestionForm, BindingResult bindingResult, Model model) {
        LOGGER.info(addQuestionForm);

        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                FieldError fieldError = (FieldError) objectError;
                LOGGER.info("error_" + fieldError.getField() + " = " + fieldError.getDefaultMessage());
                model.addAttribute("error_" + fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("error", "Возникли проблемы при валидации");
            return "adminHome";
        }

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

        model.addAttribute("successMessage", "Вопрос успешно добавлен");

        return "adminHome";
    }
}

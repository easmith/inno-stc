package Models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * Created by eku on 29.04.17.
 */
public class AddQuestionForm {

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<Integer, String> getAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public void setAnswerIsCorrect(Map<Integer, String> answerIsCorrect) {
        this.answerIsCorrect = answerIsCorrect;
    }

    public Map<Integer, String> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<Integer, String> answer) {
        this.answer = answer;
    }

    @NotNull
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Только цифры")
    public Integer categoryId;
    @NotNull
    @Pattern(regexp = "^(SINGLE|MULTI)$", message = "SINGLE || MULTI")
    public String questionType;
    @NotNull
    public String questionText;
    @NotNull
    public Map<Integer, String> answerIsCorrect;
    @NotNull
    public Map<Integer, String> answer;

}

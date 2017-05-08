package Models.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Created by eku on 29.04.17.
 */
public class AddQuestionForm {

    @NotNull
    public Integer categoryId;
    @NotNull
    @Pattern(regexp = "^(SINGLE|MULTI)$", message = "SINGLE || MULTI")
    public String questionType;
    @NotNull
    @Size(min = 1, max = 4096, message = "От одного до 4096 символов")
    public String questionText;
    @NotNull(message = "Должен быть хотябы один правильный ответ")
    public Map<Integer, String> answerIsCorrect;
    @NotNull(message = "Должен быть хотябы один ответ")
    public Map<Integer, String> answer;

    @AssertTrue(message = "Ответы не должны быть пустыми")
    public boolean hasNotEmptyAnswers() {
        for (Map.Entry<Integer, String> entry : answer.entrySet()) {
            if (null == entry.getValue() || "".equals(entry.getValue()))
                return false;
        }
        return true;
    }
    @AssertTrue(message = "Ответы не должны быть слишком длинными")
    public boolean hasNotLargeAnswers() {
        for (Map.Entry<Integer, String> entry : answer.entrySet()) {
            if (entry.getValue().length() > 4096)
                return false;
        }
        return true;
    }

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

}

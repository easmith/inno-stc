package Services;

import Models.pojo.Answer;
import Exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 08.05.17.
 */
public interface AnswerServiceInterface {
    void addAnswer(Answer answer) throws QuizInternalException;
    List<Answer> getAnswersByQuestionId(int questionId) throws QuizInternalException;
}

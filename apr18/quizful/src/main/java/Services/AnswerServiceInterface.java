package Services;

import Models.pojo.Answer;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 08.05.17.
 */
public interface AnswerServiceInterface {
    void addAnswer(Answer answer) throws QuizInternalException;
}

package Models.dao;

import Models.pojo.Answer;
import Exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 01.05.17.
 */
public interface AnswerDaoInterface {
    List<Answer> getAnswersByQuestionId(int taskId) throws QuizInternalException;
    void addAnswer(Answer answer) throws QuizInternalException;
}

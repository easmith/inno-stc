package Models.dao;

import Models.pojo.Answer;
import exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 01.05.17.
 */
public interface AnswerDaoInterface {
    List<Answer> getAnswersByTaskId(int taskId) throws QuizInternalException;
}

package Models.dao;

import Models.pojo.Question;
import Models.pojo.QuestionResult;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 30.04.17.
 */
public interface QuestionResultDaoInterface {
    QuestionResult createQuestionResult(Question question, int userId, String answers, boolean isCorrect) throws QuizInternalException;
}

package Services;

import Models.pojo.Question;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 07.05.17.
 */
public interface QuestionServiceInterface {
    Question getQuestionByCategoryId(int categoryId) throws QuizInternalException;
    void addQuestion(Question question) throws QuizInternalException;
    Question getById(int id) throws QuizInternalException;
}

package Services;

import Models.pojo.Question;
import exceptions.QuizInternalException;

/**
 * Created by eku on 07.05.17.
 */
public interface QuestionServiceInterface {
    Question getQuestionByCategoryId(int categoryId) throws QuizInternalException;
}

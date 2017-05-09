package Models.dao;

import Models.pojo.Question;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 01.05.17.
 */
public interface QuestionDaoInterface {
    /**
     * Возвращает случайный вопрос по id категории
     *
     * @param categoryId
     * @return Question
     * @throws QuizInternalException
     */
    Question getByCategoryId(int categoryId) throws QuizInternalException;

    void addQuestion(Question question) throws QuizInternalException;
}

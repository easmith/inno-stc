package Services;

import Models.dao.QuestionDaoInterface;
import Models.dao.QuestionResultDaoInterface;
import Models.pojo.Question;
import exceptions.QuizInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eku on 07.05.17.
 */
@Service
public class QuestionService implements QuestionServiceInterface {
    private static QuestionDaoInterface questionDao;

    @Autowired
    public static void setQuestionDao(QuestionDaoInterface questionDao) {
        QuestionService.questionDao = questionDao;
    }

    @Override
    public Question getQuestionByCategoryId(int categoryId) throws QuizInternalException {
        return null;
    }
}

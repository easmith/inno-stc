package Services;

import Models.dao.QuestionResultDaoInterface;
import Models.pojo.Question;
import Models.pojo.QuestionResult;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eku on 30.04.17.
 */
@Service
public class QuestionResultService implements QuestionResultServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(QuestionResultService.class);
    private static QuestionResultDaoInterface resultTaskDao;

    @Autowired
    public void setResultTaskDao(QuestionResultDaoInterface resultTaskDao) {
        QuestionResultService.resultTaskDao = resultTaskDao;
    }

    @Override
    public QuestionResult createQuestionResult(Question question, int userId, String answers, boolean isCorrect) throws QuizInternalException {
        return resultTaskDao.createQuestionResult(question, userId, answers, isCorrect);
    }

}

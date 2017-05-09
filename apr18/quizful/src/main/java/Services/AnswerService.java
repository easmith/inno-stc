package Services;

import Models.dao.AnswerDaoInterface;
import Models.pojo.Answer;
import Exceptions.QuizInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eku on 08.05.17.
 */
@Service
public class AnswerService implements AnswerServiceInterface {
    private static AnswerDaoInterface answerDao;

    @Autowired
    public void setAnswerDao(AnswerDaoInterface answerDao) {
        AnswerService.answerDao = answerDao;
    }

    @Override
    public void addAnswer(Answer answer) throws QuizInternalException {
        answerDao.addAnswer(answer);
    }
}

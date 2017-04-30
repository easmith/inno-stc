package Services;

import Models.dao.ResultTaskDaoInterface;
import Models.pojo.Result;
import Models.pojo.ResultTask;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Service
public class ResultTaskService implements ResultTaskServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private static ResultTaskDaoInterface resultTaskDao;

    @Autowired
    public void setResultTaskDao(ResultTaskDaoInterface resultTaskDao) {
        ResultTaskService.resultTaskDao = resultTaskDao;
    }

    @Override
    public List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException {
        return resultTaskDao.getResultTasksByResultId(resultId);
    }

    @Override
    public void saveAnswers(HashMap<Integer, String> answers) throws QuizInternalException {
        resultTaskDao.saveAnswers(answers);
    }

    @Override
    public void createResultTasksByResult(Result result) throws QuizInternalException {
        resultTaskDao.createResultTasksByResult(result);
    }

}

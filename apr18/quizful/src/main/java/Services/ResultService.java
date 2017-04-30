package Services;

import Models.dao.ResultDaoInterface;
import Models.pojo.Result;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Service
public class ResultService implements ResultServiceInterface {
    private static final Logger LOGGER = Logger.getLogger(ResultService.class);
    private static ResultDaoInterface resultDao;

    @Autowired
    public void setCategoryDao(ResultDaoInterface resultDao) {
        ResultService.resultDao = resultDao;
    }

    @Override
    public void updateResult(Result result) throws QuizInternalException {
        resultDao.updateResult(result);
    }

    @Override
    public Result getResultsById(int resultId) throws QuizInternalException {
        return resultDao.getResultById(resultId);
    }

    @Override
    public List<Result> getResultsByUserId(int userId) throws QuizInternalException {
        return resultDao.getResultsByUserId(userId);
    }

    public Result createResult(int userId, int categoryId) throws QuizInternalException {
        return resultDao.createResult(userId, categoryId);
    }
}

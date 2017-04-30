package Models.dao;

import Models.pojo.Result;
import exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultDaoInterface {
    Result createResult(int userId, int categoryId) throws QuizInternalException;
    List<Result> getResultsByUserId(int userId) throws QuizInternalException;
    public void updateResult(Result result) throws QuizInternalException;
    public Result getResultById(int id) throws QuizInternalException;
}

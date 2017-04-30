package Services;

import Models.pojo.Result;
import exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultServiceInterface {
    public void updateResult(Result result) throws QuizInternalException;
    public Result getResultsById(int resultId) throws QuizInternalException;
    List<Result> getResultsByUserId(int userId) throws QuizInternalException;
}

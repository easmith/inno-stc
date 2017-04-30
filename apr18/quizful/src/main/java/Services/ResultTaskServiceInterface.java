package Services;

import Models.pojo.Result;
import Models.pojo.ResultTask;
import exceptions.QuizInternalException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultTaskServiceInterface {
    List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException;

    void saveAnswers(HashMap<Integer, String> answers) throws QuizInternalException;

    void createResultTasksByResult(Result result) throws QuizInternalException;
}

package Models.dao;

import Models.pojo.Result;
import Models.pojo.ResultTask;
import exceptions.QuizInternalException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultTaskDaoInterface {
    List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException;

    ResultTask createResultTasks(int taskId, int resultId) throws QuizInternalException;

    List<ResultTask> createResultTasksByResult(Result result) throws QuizInternalException;

    void saveAnswers(HashMap<Integer, String> answers) throws QuizInternalException;
}

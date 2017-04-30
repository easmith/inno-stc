package Models.dao;

import Models.pojo.ResultTask;
import Models.pojo.Task;
import exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultTaskDaoInterface {
    List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException;
}

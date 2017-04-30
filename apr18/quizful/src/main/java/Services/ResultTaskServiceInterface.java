package Services;

import Models.dao.ResultTaskDaoInterface;
import Models.pojo.ResultTask;
import exceptions.QuizInternalException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultTaskServiceInterface {
    List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException;
}

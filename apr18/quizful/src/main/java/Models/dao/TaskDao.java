package Models.dao;

import Models.pojo.Result;
import Models.pojo.Task;
import exceptions.QuizInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class TaskDao implements TaskDaoInterface {
    private static AnswerDaoInterface answerDao;

    @Autowired
    public void setTaskDao(AnswerDaoInterface answerDao) {
        TaskDao.answerDao = answerDao;
    }

    @Override
    public Task createFromResultSet(ResultSet resultSet) throws SQLException, QuizInternalException {
        resultSet.next();
        Task task = new Task(
            resultSet.getInt("id"),
            resultSet.getString("text"),
            resultSet.getInt("category_id")
        );

        task.setAnswers(answerDao.getAnswersByTaskId(task.getId()));

        return task;
    }
}

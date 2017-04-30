package Models.dao;

import Models.pojo.*;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Repository
public class ResultTaskDao implements ResultTaskDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(ResultTaskDao.class);

    @Override
    public List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException {
        List<ResultTask> resultTasks = new ArrayList<>();

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT rt.id id, t.id task_id, t.text task_text, a.text answer_text FROM result_tasks AS rt JOIN tasks AS t ON t.id = rt.task_id JOIN answers AS a ON a.task_id = t.id WHERE rt.result_id = ?");
            statement.setInt(1, resultId);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("Query");
            HashMap<Integer, ResultTask> resultTasksMap = new HashMap<>();
            while (resultSet.next()) {
                int resultTaskId = resultSet.getInt("id");
                ResultTask resultTask = null;
                if (resultTasksMap.containsKey(resultTaskId)) {
                    resultTask = (ResultTask)resultTasksMap.get(resultTaskId);
                } else {
                    resultTask = new ResultTask();
                }
                resultTask.setId(resultTaskId);

                Task task = resultTask.getTask();
                if (task == null) {
                    int taskId = resultSet.getInt("task_id");
                    resultTask.setTaskId(taskId);
                    task = new Task();
                    task.setText(resultSet.getString("task_text"));
                    resultTask.setTask(task);
                }

                ArrayList<Answer> answers = task.getAnswers();
                if (answers == null) {
                    answers = new ArrayList<>();
                }
                Answer answer = new Answer();
                answer.setText(resultSet.getString("answer_text"));
                answers.add(answer);

                task.setAnswers(answers);
                resultTasksMap.put(resultTaskId, resultTask);
            }
            LOGGER.info("ResultTask size: " + resultTasksMap.size());
            for (ResultTask resultTask :
                    resultTasksMap.values()) {
                resultTasks.add(resultTask);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return resultTasks;
    }
}

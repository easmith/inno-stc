package Models.dao;

import Models.pojo.Answer;
import Models.pojo.Result;
import Models.pojo.ResultTask;
import Models.pojo.Task;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eku on 30.04.17.
 */
@Repository
public class ResultTaskDao implements ResultTaskDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(ResultTaskDao.class);
    private static TaskDaoInterface taskDao;

    @Autowired
    public void setTaskDao(TaskDaoInterface taskDao) {
        ResultTaskDao.taskDao = taskDao;
    }

    @Override
    public List<ResultTask> getResultTasksByResultId(int resultId) throws QuizInternalException {
        List<ResultTask> resultTasks = new ArrayList<>();

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT rt.id id, " +
                            "t.id task_id, t.category_id task_category, t.text task_text, " +
                            "a.id answer_id, a.text answer_text, a.is_correct answer_is_correct " +
                            "FROM result_tasks AS rt JOIN tasks AS t ON t.id = rt.task_id JOIN answers AS a ON a.task_id = t.id " +
                            "WHERE rt.result_id = ?");
            statement.setInt(1, resultId);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("Query");
            HashMap<Integer, ResultTask> resultTasksMap = new HashMap<>();
            while (resultSet.next()) {
                int resultTaskId = resultSet.getInt("id");
                ResultTask resultTask = null;
                if (resultTasksMap.containsKey(resultTaskId)) {
                    resultTask = (ResultTask) resultTasksMap.get(resultTaskId);
                } else {
                    resultTask = new ResultTask(
                            resultSet.getInt("id"),
                            resultSet.getInt("task_id"), resultId);
                }

                Task task = resultTask.getTask();
                if (task == null) {
                    int taskId = resultSet.getInt("task_id");
                    resultTask.setTaskId(taskId);
                    task = new Task(
                            taskId,
                            resultSet.getString("task_text"),
                            resultSet.getInt("task_category")
                    );
                    resultTask.setTask(task);
                }

                List<Answer> answers = task.getAnswers();
                if (answers == null) {
                    answers = new ArrayList<>();
                }
                Answer answer = new Answer(resultSet.getInt("answer_id"),
                        resultSet.getString("answer_text"),
                        resultSet.getBoolean("answer_is_correct")
                );
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

    @Override
    public ResultTask createResultTasks(int taskId, int resultId) throws QuizInternalException {
        LOGGER.info("Создаем ResultTask: taskId=" + taskId + ", resultId=" + resultId);
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO result_tasks (task_id, result_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, taskId);
            statement.setInt(2, resultId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return new ResultTask(generatedKeys.getInt(1), taskId, resultId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }


    @Override
    public List<ResultTask> createResultTasksByResult(Result result) throws QuizInternalException {
        List<ResultTask> resultTasks = new ArrayList<>();

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tasks WHERE category_id = ? ORDER BY RAND() LIMIT 10");
            statement.setInt(1, result.getCategoryId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = taskDao.createFromResultSet(resultSet);
                ResultTask resultTask = createResultTasks(task.getId(), result.getId());
                resultTask.setTask(task);
                resultTask.setResult(result);
                resultTasks.add(resultTask);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return resultTasks;
    }

    @Override
    public void saveAnswers(HashMap<Integer, String> answersMap) throws QuizInternalException {
        for (Map.Entry<Integer, String> entry :
                answersMap.entrySet()) {
            this.setAnswerToResultTask(entry.getKey(), entry.getValue());
        }
    }

    public void setAnswerToResultTask(int resultTaskId, String answer) throws QuizInternalException {
        LOGGER.info("update resultTask: " + resultTaskId + " [" + answer + "]");
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE result_tasks SET answers = ? WHERE id = ?");
            statement.setString(1, answer);
            statement.setInt(2, resultTaskId);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }
}

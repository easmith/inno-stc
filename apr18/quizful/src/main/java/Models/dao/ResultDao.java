package Models.dao;

import Models.pojo.Result;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Repository
public class ResultDao implements ResultDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(ResultDao.class);

    /**
     * Создает новый результат теста
     *
     * @param categoryId id Категории
     * @param userId     id пользователя
     * @return
     * @throws QuizInternalException
     */
    @Override
    public Result createResult(int userId, int categoryId) throws QuizInternalException {
        LOGGER.info("Создаем Result: categoryId=" + categoryId + ", userId=" + userId);
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO results (category_id, user_id, start_at) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, categoryId);
            statement.setInt(2, userId);
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getResultById(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }

    @Override
    public void updateResult(Result result) throws QuizInternalException {
        LOGGER.info("update result");
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE results SET stop_at = ? WHERE id = ?");
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setInt(2, result.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }

    /**
     * Возвращает результаты теста по указанному id пользователя
     *
     * @param userId id пользователя
     * @return
     */
    @Override
    public List<Result> getResultsByUserId(int userId) throws QuizInternalException {
        List results = new ArrayList();
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM results WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("query ok");
            while (resultSet.next()) {
                results.add(new Result(
                        resultSet.getInt("id"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("start_at"),
                        resultSet.getTimestamp("stop_at")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
        return results;
    }

    @Override
    public Result getResultById(int id) throws QuizInternalException {
        Result result = null;
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM results WHERE id = ? LIMIT 1");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("query ok");
            while (resultSet.next()) {
                result = new Result(
                        resultSet.getInt("id"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("start_at"),
                        resultSet.getTimestamp("stop_at")
                );
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
        return result;
    }
}

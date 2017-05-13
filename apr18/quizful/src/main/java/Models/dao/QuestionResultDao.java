package Models.dao;

import Models.pojo.Question;
import Models.pojo.QuestionResult;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by eku on 30.04.17.
 */
@Repository
public class QuestionResultDao implements QuestionResultDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(QuestionResultDaoInterface.class);
    private static QuestionDaoInterface taskDao;

    private DriverManagerDataSource driverManagerDataSource;

    @Autowired
    public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    @Autowired
    public void setTaskDao(QuestionDaoInterface taskDao) {
        QuestionResultDao.taskDao = taskDao;
    }


    @Override
    public QuestionResult createQuestionResult(Question question, int userId, String answers, boolean isCorrect) throws QuizInternalException {
        LOGGER.info("Создаем QuestionResult: questionId=" + question.getId() + ", userId=" + userId + ", answers=" + answers);
        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO question_results (question_id, category_id, user_id, answers, is_correct) " +
                            "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, question.getId());
            statement.setInt(2, question.getCategoryId());
            statement.setInt(3, userId);
            statement.setString(4, answers);
            statement.setBoolean(5, isCorrect);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return new QuestionResult(generatedKeys.getInt(1),
                        question.getId(), userId,
                        question.getCategoryId(),
                        answers, isCorrect);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }
}

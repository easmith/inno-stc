package Models.dao;

import Models.pojo.Question;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class QuestionDao implements QuestionDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);

    private static AnswerDaoInterface answerDao;
    private DriverManagerDataSource driverManagerDataSource;

    @Autowired
    public void setAnswerDao(AnswerDaoInterface answerDao) {
        QuestionDao.answerDao = answerDao;
    }

    @Autowired
    public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    protected Question createFromResultSet(ResultSet resultSet) throws SQLException, QuizInternalException {
        Question question = new Question(
                resultSet.getInt("id"),
                resultSet.getString("type"),
                resultSet.getString("text"),
                resultSet.getInt("category_id")
        );

        question.setAnswers(answerDao.getAnswersByQuestionId(question.getId()));

        return question;
    }

    @Override
    public Question getByCategoryId(int categoryId) throws QuizInternalException {
        Question question = null;

        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM questions WHERE category_id = ? ORDER BY RAND() LIMIT 1");
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return question;
    }

    @Override
    public void addQuestion(Question question) throws QuizInternalException {
        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO questions (category_id, text, type) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, question.getCategoryId());
            statement.setString(2, question.getText());
            statement.setString(3, question.getType());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                question.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }
}

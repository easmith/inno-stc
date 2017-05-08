package Models.dao;

import Models.pojo.Answer;
import Models.pojo.Question;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class AnswerDao implements AnswerDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(AnswerDao.class);

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) throws QuizInternalException {
        List<Answer> answers = new ArrayList();
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM answers WHERE question_id = ?");
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("query ok");
            while (resultSet.next()) {
                answers.add(new Answer(
                        resultSet.getInt("id"),
                        questionId,
                        resultSet.getString("text"),
                        resultSet.getBoolean("is_correct")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
        return answers;
    }

    @Override
    public void addAnswer(Answer answer) throws QuizInternalException {
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO answers (question_id, text, is_correct) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, answer.getQuestionId());
            statement.setString(2, answer.getText());
            statement.setBoolean(3, answer.getCorrect());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                answer.setId(generatedKeys.getInt(1));
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

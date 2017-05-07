package Models.dao;

import Models.pojo.Answer;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}

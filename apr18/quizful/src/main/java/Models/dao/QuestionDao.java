package Models.dao;

import Models.pojo.Question;
import Utils.DbConnectionFactory;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class QuestionDao implements QuestionDaoInterface {
    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);
    private static AnswerDaoInterface answerDao;

    @Autowired
    public void setTaskDao(AnswerDaoInterface answerDao) {
        QuestionDao.answerDao = answerDao;
    }

    protected Question createFromResultSet(ResultSet resultSet) throws SQLException, QuizInternalException {
        resultSet.next();
        Question task = new Question(
                resultSet.getInt("id"),
                resultSet.getString("text"),
                resultSet.getInt("category_id")
        );

        task.setAnswers(answerDao.getAnswersByQuestionId(task.getId()));

        return task;
    }

    @Override
    public Question getByCategoryId(int categoryId) throws QuizInternalException {
        Question question = null;

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
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
}

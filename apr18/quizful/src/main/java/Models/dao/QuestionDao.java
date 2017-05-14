package Models.dao;

import Models.pojo.Answer;
import Models.pojo.Question;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class QuestionDao implements QuestionDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);

    private static AnswerDaoInterface answerDao;
    private DriverManagerDataSource driverManagerDataSource;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
        Session session = this.sessionFactory.openSession();
        Question question = (Question) session.createQuery("from Question where categoryId = :categoryId ORDER BY RAND()")
                .setParameter("categoryId", categoryId)
                .setMaxResults(1)
                .uniqueResult();
        session.close();
        return question;
    }

    @Override
    public Question getById(int id) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        Question question = session.find(Question.class, id);
        session.close();
        return question;
    }

    @Override
    public void addQuestion(Question question) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(question);
        tx.commit();
        session.close();
    }
}

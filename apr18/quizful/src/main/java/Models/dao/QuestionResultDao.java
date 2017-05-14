package Models.dao;

import Models.pojo.Question;
import Models.pojo.QuestionResult;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Autowired
    public void setTaskDao(QuestionDaoInterface taskDao) {
        QuestionResultDao.taskDao = taskDao;
    }


    @Override
    public QuestionResult createQuestionResult(Question question, int userId, String answers, boolean isCorrect) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        QuestionResult questionResult = new QuestionResult(0, question.getId(), userId, question.getCategoryId(), answers, isCorrect);
        session.persist(questionResult);
        tx.commit();
        session.close();
        return questionResult;
    }
}

package Models.dao;

import Exceptions.QuizInternalException;
import Models.pojo.Answer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eku on 01.05.17.
 */
@Repository
public class AnswerDao implements AnswerDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(AnswerDao.class);

    private DriverManagerDataSource driverManagerDataSource;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        List<Answer> answers = session.createQuery("from Answer where questionId = :questionId")
                .setParameter("questionId", questionId)
                .list();
        session.close();
        return answers;
    }

    @Override
    public void addAnswer(Answer answer) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(answer);
        tx.commit();
        session.close();
    }
}

package Models.dao;

import Exceptions.QuizInternalException;
import Models.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by eku on 24.04.17.
 */
@Repository
public class UserDao implements UserDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) throws QuizInternalException {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        LOGGER.info("Add user '" + user.getLogin() + "'");
    }

    @Override
    public User findUserByLogin(String login) throws QuizInternalException {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.createQuery("from User where login = :login")
                .setParameter("login", login)
                .uniqueResult();
        tx.commit();
        LOGGER.info("Find user by login '" + login + "' = " + user);
        return user;
    }
}

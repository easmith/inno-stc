package Models.dao;

import Models.pojo.Answer;
import Models.pojo.Category;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Repository
public class CategoryDao implements CategoryDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(CategoryDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getCategories() throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        List<Category> answers = session.createQuery("from Category").list();
        session.close();
        return answers;
    }

    @Override
    public Category getCategoryById(int categoryId) throws QuizInternalException {
        Session session = this.sessionFactory.openSession();
        Category category = (Category) session.createQuery("from Category where id = :categoryId")
                .setParameter("categoryId", categoryId)
                .uniqueResult();
        session.close();
        return category;
    }
}

package Models.dao;

import Models.pojo.Category;
import Models.pojo.User;
import Utils.DbConnectionFactory;
import Utils.PasswordManager;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
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

    @Override
    public List<Category> getCategories() throws QuizInternalException {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.name, count(q.id) FROM categories AS c LEFT JOIN questions AS q ON c.id = q.category_id GROUP BY c.id");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                categories.add(new Category(result.getInt("id"), result.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) throws QuizInternalException {
        Category category = null;
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.name, count(t.id) FROM categories AS c LEFT JOIN tasks AS t ON c.id = t.category_id WHERE c.id = ? GROUP BY c.id LIMIT 1");
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                category = new Category(result.getInt("id"), result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return category;
    }
}

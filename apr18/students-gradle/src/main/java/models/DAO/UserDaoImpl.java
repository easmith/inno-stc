package models.DAO;

import controllers.LoginController;
import models.POJO.User;
import org.apache.log4j.Logger;
import services.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eku on 20.04.17.
 */
public class UserDaoImpl implements UserDao{

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User m = new User(result.getInt("id"), result.getString("login"), result.getString("password"), result.getBoolean("is_blocked"));
                return m;
            }
            LOGGER.debug("User " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }

    protected User createEntity(ResultSet resultSet) {
        User user = null;
        try {
            user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getBoolean("is_blocked"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

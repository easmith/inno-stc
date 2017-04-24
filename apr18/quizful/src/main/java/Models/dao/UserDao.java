package Models.dao;

import Models.pojo.User;
import Utils.DbConnectionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eku on 24.04.17.
 */
public class UserDao implements UserDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User m = new User(result.getInt("id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getBoolean("is_admin"));
                return m;
            }
            LOGGER.debug("User " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }
}

package Models.dao;

import Models.pojo.User;
import Utils.DbConnectionFactory;
import Utils.PasswordManager;
import com.mysql.jdbc.JDBC4ResultSet;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by eku on 24.04.17.
 */
public class UserDao implements UserDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws QuizInternalException {
        User user = null;

        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? LIMIT 1");
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            LOGGER.debug("executed " + login);
            while (result.next()) {
                LOGGER.debug("found " + result.getInt("id"));
                LOGGER.debug("check " + password + " = " + result.getString("password"));
                if (PasswordManager.checkHash(password, result.getString("password"))) {
                    LOGGER.debug("password ok");
                    return new User(result.getInt("id"),
                            result.getString("name"),
                            result.getString("login"),
                            password,
                            result.getBoolean("is_admin"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return user;
    }

    @Override
    public void addUser(User user) throws QuizInternalException {
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, login, password, is_admin) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, PasswordManager.createHash(user.getPassword()));
            statement.setBoolean(4, user.getAdmin());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
    }

    @Override
    public boolean existUser(String login) throws QuizInternalException {
        boolean result = false;
        try (Connection connection = DbConnectionFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? LIMIT 1");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = true;
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
        return result;
    }


}

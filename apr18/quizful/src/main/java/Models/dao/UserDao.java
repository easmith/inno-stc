package Models.dao;

import Models.pojo.User;
import Utils.PasswordManager;
import Exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by eku on 24.04.17.
 */
@Repository
public class UserDao implements UserDaoInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private DriverManagerDataSource driverManagerDataSource;

    @Autowired
    public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws QuizInternalException {
        User user = null;

        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? LIMIT 1");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (PasswordManager.checkHash(password, resultSet.getString("password"))) {
                    return createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }

        return user;
    }

    protected User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("login"),
                "",
                resultSet.getString("name"),
                resultSet.getString("role"),
                resultSet.getBoolean("enabled"));
    }

    @Override
    public void addUser(User user) throws QuizInternalException {
        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, login, password, enabled, role) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, PasswordManager.createHash(user.getPassword()));
            statement.setBoolean(4, true);
            statement.setString(5, user.getRole());
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
    public User findUserByLogin(String login) throws QuizInternalException {
        User user = null;
        try (Connection connection = driverManagerDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? LIMIT 1");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new QuizInternalException();
        }
        return user;
    }
}

package models.DAO;

import models.POJO.User;

/**
 * Created by eku on 20.04.17.
 */
public interface UserDao {
    User findUserByLoginAndPassword(String login, String password);
}

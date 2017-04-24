package Models.dao;

import Models.pojo.User;

/**
 * Created by eku on 24.04.17.
 */
public interface UserDaoInterface {
    User findUserByLoginAndPassword(String login, String password);
}

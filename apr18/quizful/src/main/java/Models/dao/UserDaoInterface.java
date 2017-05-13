package Models.dao;

import Models.pojo.User;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 24.04.17.
 */
public interface UserDaoInterface {
    User findUserByLoginAndPassword(String login, String password) throws QuizInternalException;
    void addUser(User user) throws QuizInternalException;
    User findUserByLogin(String login) throws QuizInternalException;
}

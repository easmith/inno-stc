package Models.dao;

import Models.pojo.User;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 24.04.17.
 */
public interface UserDaoInterface {
    void addUser(User user) throws QuizInternalException;
    User findUserByLogin(String login) throws QuizInternalException;
}

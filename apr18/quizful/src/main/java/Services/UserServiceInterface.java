package Services;

import Models.pojo.User;
import exceptions.QuizInternalException;

/**
 * Created by eku on 24.04.17.
 */
public interface UserServiceInterface {
    User auth(String login, String password) throws QuizInternalException;
    User findUserByLogin(String login) throws QuizInternalException;
    void addUser(User user) throws QuizInternalException;
}

package Services;

import Models.pojo.User;
import Exceptions.QuizInternalException;

/**
 * Created by eku on 24.04.17.
 */
public interface UserServiceInterface {
    User findUserByLogin(String login) throws QuizInternalException;
    void addUser(User user) throws QuizInternalException;
}

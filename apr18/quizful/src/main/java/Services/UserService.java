package Services;

import Models.dao.UserDao;
import Models.dao.UserDaoInterface;
import Models.pojo.User;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;

/**
 * Created by eku on 24.04.17.
 */
public class UserService implements UserServiceInterface {

    private static UserDaoInterface userDao = new UserDao();

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Override
    public User auth(String login, String password) throws QuizInternalException {
        User user = userDao.findUserByLoginAndPassword(login, password);
        return user;
    }

    @Override
    public boolean existUser(String login) throws QuizInternalException {
        return userDao.existUser(login);
    }

    @Override
    public void addUser(User user) throws QuizInternalException {
        userDao.addUser(user);
    }
}

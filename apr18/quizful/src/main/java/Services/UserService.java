package Services;

import Models.dao.UserDaoInterface;
import Models.pojo.User;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eku on 24.04.17.
 */
@Service
public class UserService implements UserServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private static UserDaoInterface userDao;

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
        UserService.userDao = userDao;
    }

    @Override
    public User auth(String login, String password) throws QuizInternalException {
        User user = userDao.findUserByLoginAndPassword(login, password);
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws QuizInternalException {
        return userDao.findUserByLogin(login);
    }

    @Override
    public void addUser(User user) throws QuizInternalException {
        userDao.addUser(user);
    }
}

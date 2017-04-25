package services;

import models.DAO.UserDao;
import models.DAO.UserDaoImpl;
import models.POJO.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by eku on 20.04.17.
 */
@Component
public class UserServiceImpl implements UserServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static UserDao userDao = new UserDaoImpl();
//    private UserDaoImpl userDao;


    @Override
    public User auth(String login, String password) {
        User user = userDao.findUserByLoginAndPassword(login, password);
        LOGGER.debug("User: " + user);
        if (user != null && user.getBlocked()) {
            return null;
        }
        LOGGER.debug("User not blocked");
        return user;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

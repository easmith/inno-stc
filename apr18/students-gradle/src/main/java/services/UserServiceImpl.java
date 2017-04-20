package services;

import controllers.LoginController;
import models.DAO.UserDao;
import models.DAO.UserDaoImpl;
import models.POJO.User;
import org.apache.log4j.Logger;

/**
 * Created by eku on 20.04.17.
 */
public class UserServiceImpl implements UserServiceInterface {

    private static UserDao userDao = new UserDaoImpl();


    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

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
}

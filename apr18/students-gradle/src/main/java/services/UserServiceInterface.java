package services;

import models.POJO.User;

/**
 * Created by eku on 20.04.17.
 */
public interface UserServiceInterface {
    User auth(String login, String password);
}

package Services;

import Models.pojo.User;

/**
 * Created by eku on 24.04.17.
 */
public interface UserServiceInterface {
    User auth(String login, String password);
    boolean existUser(String login);
    void addUser(User user);
}

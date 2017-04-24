package Services;

import Models.pojo.User;

/**
 * Created by eku on 24.04.17.
 */
public interface UserServiceInterface {
    User auth(String login, String password);
}

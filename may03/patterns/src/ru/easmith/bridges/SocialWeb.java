package ru.easmith.bridges;

import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public interface SocialWeb {
    List<String> getFriends(int userId);
    void pay(int userId, int amount);
    List<String> wall(int userId);
}

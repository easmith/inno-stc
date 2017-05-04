package ru.easmith.bridges;

import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public interface DB {
    List<String> getListOfFriends(int userId);
    Boolean transaction(int userId, int amount);
    List<String> getWall(int userId);
    List<Integer> getTransactions(int userId);
}

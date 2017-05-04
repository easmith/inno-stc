package ru.easmith.bridges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class BasicSocialWeb implements SocialWeb {
    private List<String> friends = new ArrayList<>();
    private List<String> posts = Arrays.asList("post1", "post2", "post3");
    private Integer money = 0;

    private static DB postgresDB = new PostgresDB();

    @Override
    public List<String> getFriends(int userId) {
        return postgresDB.getListOfFriends(userId);
    }

    @Override
    public void pay(int userId, int amount) {
        postgresDB.transaction(userId, amount);
    }

    public List<Integer> getTransactions(int userId) {
        return postgresDB.getTransactions(userId);
    }

    @Override
    public List<String> wall(int userId) {
        return postgresDB.getWall(userId);
    }
}

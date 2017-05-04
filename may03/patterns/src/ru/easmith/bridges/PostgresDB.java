package ru.easmith.bridges;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/**
 * Created by eku on 04.05.17.
 */
public class PostgresDB implements DB {
    Map<Integer, List<Integer>> transactions = new HashMap<>();

    @Override
    public List<String> getListOfFriends(int userId) {
        Map<Integer, List> friends = new HashMap<>();
        friends.put(1, Arrays.asList("Misha1", "Vova1", "Andrey1"));
        friends.put(2, Arrays.asList("Katya2", "Petya2", "Max2"));
        return friends.get(userId);
    }

    @Override
    public Boolean transaction(int userId, int amount) {
        if (transactions.get(userId) == null) {
            transactions.put(userId, new ArrayList<>());
        }
        transactions.get(userId).add(amount);
        return true;
    }

    @Override
    public List<String> getWall(int userId) {
        Map<Integer, List> posts = new HashMap<>();
        posts.put(1, Arrays.asList("post11", "post12", "post13"));
        posts.put(2, Arrays.asList("post21", "post22", "post23"));
        return posts.get(userId);
    }

    @Override
    public List<Integer> getTransactions(int userId) {
        return transactions.get(userId);
    }
}

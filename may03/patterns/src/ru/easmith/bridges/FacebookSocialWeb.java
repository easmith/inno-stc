package ru.easmith.bridges;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class FacebookSocialWeb extends BasicSocialWeb {

    private static DB postgresDB = new PostgresDB();

    String getHistory(int userId, String date) {
        return "history from facebook";
    }

    List<Integer> getLikes(int messageId, boolean showNegative, int userId) {
        return Arrays.asList(43, 3434, 565);
    }
}

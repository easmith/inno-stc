package ru.easmith.bridges;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class VkSocialWeb extends BasicSocialWeb {

    public String getHistory(int userId) {
        return "VkSocialWeb";
    }

    List<Integer> getLikes(int messageId, int userId) {
        return Arrays.asList(1, 2);
    }
}

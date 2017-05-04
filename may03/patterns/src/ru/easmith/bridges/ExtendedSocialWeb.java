package ru.easmith.bridges;

import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public interface ExtendedSocialWeb {
    String getHistory(int userId, String date);
    List<Integer> getLikes(int userId, boolean showNegative, int postId);
}

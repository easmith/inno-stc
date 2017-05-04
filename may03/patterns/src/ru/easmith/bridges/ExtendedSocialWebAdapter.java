package ru.easmith.bridges;

import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class ExtendedSocialWebAdapter implements ExtendedSocialWeb {

    private BasicSocialWeb basicSocialWeb;

    public ExtendedSocialWebAdapter(BasicSocialWeb basicSocialWeb) {
        this.basicSocialWeb = basicSocialWeb;
    }

    @Override
    public String getHistory(int userId, String date) {
        if (basicSocialWeb instanceof VkSocialWeb) {
            return new VkSocialWeb().getHistory(userId);
        }
        if (basicSocialWeb instanceof FacebookSocialWeb) {
            return new FacebookSocialWeb().getHistory(userId, "date");
        }
        return null;
    }

    @Override
    public List<Integer> getLikes(int userId, boolean showNegative, int postId) {
        if (basicSocialWeb instanceof VkSocialWeb) {
            return new VkSocialWeb().getLikes(1, 3);
        }
        if (basicSocialWeb instanceof FacebookSocialWeb) {
            return new FacebookSocialWeb().getLikes(1, false, 3);
        }
        return null;
    }
}

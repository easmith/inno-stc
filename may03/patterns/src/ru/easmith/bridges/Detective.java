package ru.easmith.bridges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class Detective {

    String getResume(int userId) {

        List<BasicSocialWeb> socialWebs = getSocialWebs();
        int totalFriends = 0;
        for (SocialWeb socialWeb :
                socialWebs) {
            totalFriends += socialWeb.getFriends(userId).size();
        }

        String histories = "";
        for (BasicSocialWeb socialWeb :
                socialWebs) {
            ExtendedSocialWebAdapter adapter = new ExtendedSocialWebAdapter(socialWeb);
            histories += "\t" + socialWeb.getClass().getName() + ": " + adapter.getHistory(userId, "date") + "\n";
        }

        return "Resume by userId " + userId + "\n" +
                    "total friends: " + totalFriends + "\n" +
                    "histories: \n" + histories + "\nend of histories";
    }

    protected List<BasicSocialWeb> getSocialWebs() {
        return Arrays.asList(new FacebookSocialWeb(), new VkSocialWeb());
    }

}

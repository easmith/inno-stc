package ru.easmith.bridges;

import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class Inspector extends Detective {
    public String paymentsDetails(int userId) {

        List<BasicSocialWeb> socialWebs = getSocialWebs();

        String details = "Payments details\n";
        for (BasicSocialWeb socialWeb :
                socialWebs) {
            details += "\t" + socialWeb.getClass().getName() + ": " + socialWeb.getTransactions(userId) + "\n";
        }

        return details;
    }
}

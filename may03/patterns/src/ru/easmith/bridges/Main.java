package ru.easmith.bridges;

/**
 * Created by eku on 04.05.17.
 */
public class Main {
    public static void main(String[] args) {
        BasicSocialWeb facebookSocialWeb = new FacebookSocialWeb();
        ExtendedSocialWeb extendedSocialWeb = new ExtendedSocialWebAdapter(facebookSocialWeb);

        System.out.println(extendedSocialWeb.getHistory(1, "date"));
        System.out.println(extendedSocialWeb.getLikes(1, false, 2));

        Detective detective = new Detective();
        System.out.println(detective.getResume(1));


        facebookSocialWeb.pay(1, 100);
        facebookSocialWeb.pay(1, -10);

        Inspector inspector = new Inspector();
        System.out.println(inspector.paymentsDetails(1));

    }
}

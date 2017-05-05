package ru.easmith.chain;

/**
 * Created by eku on 05.05.17.
 */
public class BabkiRumor extends Rumor {

    private boolean isLive = true;

    @Override
    public void obs() {
        if (!isLive) {
            System.out.println("Sorry, Babka is dead");
            return;
        }

        System.out.println("babka said");
        super.obs();
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}

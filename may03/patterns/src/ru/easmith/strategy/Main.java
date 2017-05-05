package ru.easmith.strategy;

/**
 * Created by eku on 05.05.17.
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        context.setHasFamily(true);
        context.setHasFriends(true);
        context.setCash(10000);

        context.celebrate();
    }
}

package ru.easmith.chain;

/**
 * Created by eku on 05.05.17.
 */
public class InternetRumor extends Rumor {

    private boolean isConnected = true;

    @Override
    public void obs() {
        if (!isConnected) {
            System.out.println("Отсутствует интернет соединение");
            return;
        }

        System.out.println("newspaper write");
        super.obs();
    }
}

package ru.easmith.chain;

/**
 * Created by eku on 05.05.17.
 */
public class Main {
    public static void main(String[] args) {
        BabkiRumor babkiRumor = new BabkiRumor();
        babkiRumor.setMessage("String are very popular in Innopolis");
        NewspaperRumor newspaperRumor = new NewspaperRumor();
        babkiRumor.setRumor(newspaperRumor);
        newspaperRumor.setBalance(8000L);
        InternetRumor internetRumor = new InternetRumor();
        newspaperRumor.setRumor(internetRumor);
        babkiRumor.obs();
    }
}

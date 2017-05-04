package ru.easmith.decorator;

/**
 * Created by eku on 04.05.17.
 */
public class Main {
    public static void main(String[] args) {
        MainTariff mainTariff = new MainTariff();
        SMSTariff smsTariff = new SMSTariff(mainTariff);
        RoamingTariff roamingTariff = new RoamingTariff(smsTariff);
        InternetTariff internetTariff = new InternetTariff(roamingTariff);

        internetTariff.processTariff();
    }
}

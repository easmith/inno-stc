package ru.easmith.decorator;

/**
 * Created by eku on 04.05.17.
 */
public class InternetTariff extends TariffDecorator {
    public InternetTariff(CountTariffInterface tariff) {
        super(tariff);
    }

    @Override
    public void processTariff() {
        System.out.println("You have 3.5 Mb/s internet");
        super.processTariff();
    }
}

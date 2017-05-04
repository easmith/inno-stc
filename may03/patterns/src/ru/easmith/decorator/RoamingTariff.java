package ru.easmith.decorator;

/**
 * Created by eku on 04.05.17.
 */
public class RoamingTariff extends TariffDecorator {
    public RoamingTariff(CountTariffInterface tariff) {
        super(tariff);
    }

    @Override
    public void processTariff() {
        System.out.println("You have 10 day in roaming");
        super.processTariff();
    }
}

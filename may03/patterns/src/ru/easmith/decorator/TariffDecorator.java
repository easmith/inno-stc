package ru.easmith.decorator;

/**
 * Created by eku on 04.05.17.
 */
public class TariffDecorator implements CountTariffInterface {

    private CountTariffInterface tariff;

    public TariffDecorator(CountTariffInterface tariffDecorator) {
        this.tariff = tariffDecorator;
    }

    @Override
    public void processTariff() {
        tariff.processTariff();
    }
}

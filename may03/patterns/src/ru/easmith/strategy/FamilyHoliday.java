package ru.easmith.strategy;

/**
 * Created by eku on 05.05.17.
 */
public class FamilyHoliday implements HolidayStrategy {
    @Override
    public void celebrate() {
        System.out.println("Домашний новый год");
    }
}

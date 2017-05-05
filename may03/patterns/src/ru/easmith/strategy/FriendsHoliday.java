package ru.easmith.strategy;

/**
 * Created by eku on 05.05.17.
 */
public class FriendsHoliday implements HolidayStrategy {
    @Override
    public void celebrate() {
        System.out.println("Новый год с друзьями");
    }
}

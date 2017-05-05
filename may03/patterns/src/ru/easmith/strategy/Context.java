package ru.easmith.strategy;

/**
 * Created by eku on 05.05.17.
 */
public class Context {
    private HolidayStrategy holidayStrategy;
    private boolean hasFamily;
    private boolean hasFriends;

    public void setHasFamily(boolean hasFamily) {
        this.hasFamily = hasFamily;
    }

    public void setHasFriends(boolean hasFriends) {
        this.hasFriends = hasFriends;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    private int cash;

    public void celebrate() {
        if (hasFamily) {
            holidayStrategy = new FamilyHoliday();
        } else if (hasFriends && cash > 5000) {
            holidayStrategy = new FriendsHoliday();
        } else {
            holidayStrategy = new ForeverAloneHoliday();
        }

        holidayStrategy.celebrate();
    }
}

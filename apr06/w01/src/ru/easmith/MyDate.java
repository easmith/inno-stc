package ru.easmith;

import java.util.Date;

public class MyDate extends Date {

    private final long startTime;

    public MyDate() {
        this.startTime = this.getTime();
    }

    public long getCurrent() {
        return new Date().getTime();
    }

    public synchronized boolean isMultipleOf(int step) {
        long delta = (getCurrent() - startTime) / 1000;
        return (delta > 0) && (delta % step) == 0;
    }
}

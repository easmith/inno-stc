package ru.easmith;

import java.util.Calendar;

/**
 * Created by eku on 06.04.17.
 */
class Producer implements Runnable {

    private MyDate date;

    public Producer(MyDate date) {
        this.date = date;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (date) {
                    Thread.sleep(1000);
                    System.out.println("Producer: " + Calendar.getInstance().getTime().getTime());
                    date.notifyAll();
                }

            }
        } catch (InterruptedException e) {
        }
    }
}
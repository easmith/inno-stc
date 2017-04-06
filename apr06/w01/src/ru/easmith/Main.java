package ru.easmith;

import com.sun.jndi.ldap.Connection;

import javax.naming.NamingException;
import java.util.Calendar;
import java.util.Date;

class MyDate extends Date {

    private final long startTime;

    public MyDate() {
        this.startTime = this.getTime();
    }

    public long getCurrent() {
        return Calendar.getInstance().getTime().getTime();
    }

    public synchronized boolean isMultipleOf(int step) {
        long delta = getCurrent() - startTime;
        return (delta / 1000) % step == 0;
    }
}



public class Main {
    public static void main(String[] args) {

        MyDate date = new MyDate();

        System.out.println("Start on: " + date.getCurrent());

//        new Thread(new Producer(date)).start();
        new Thread(new Consumer(date, 5)).start();
        new Thread(new Consumer(date, 5)).start();

        while (true) {
            synchronized (date) {
                date.notifyAll();
                System.out.println("Main " + date.getCurrent());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        new Thread(new Consumer(date, 7)).start();


    }
}

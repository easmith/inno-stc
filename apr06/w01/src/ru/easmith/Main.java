package ru.easmith;

import java.util.Date;




public class Main {
    public static void main(String[] args) {
        MyDate date = new MyDate();
        System.out.println("Start on: " + date.getCurrent()/1000);

        new Thread(new Consumer(date, 5)).start();
        new Thread(new Consumer(date, 7)).start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (date) {
                System.out.println("Main " + date.getCurrent()/1000);
                date.notifyAll();
            }
        }
    }
}

package ru.easmith;

import com.sun.jndi.ldap.Connection;

import javax.naming.NamingException;
import java.util.Date;

class MyDate extends Date {
    public synchronized void tick() {
        notifyAll();
    }

    public synchronized void what(int step) {
        if ((this.getTime() / 1000) % step != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("By every " + step +" seconds: " + this);
    }
}


class Producer implements Runnable {

    private MyDate date;

    public Producer(MyDate date) {
        this.date = date;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println(date);
                date.tick();
            }
        } catch (InterruptedException e) {
        }
    }
}

class Consumer implements Runnable {
    private MyDate date;
    private int step;

    public Consumer(MyDate date, int step) {
        this.date = date;
        this.step = step;
    }

    @Override
    public void run() {
        date.what(step);
    }
}

public class Main {
    public static void main(String[] args) {

        MyDate date = new MyDate();

        new Thread(new Producer(date)).start();
        new Thread(new Consumer(date, 5)).start();
//        new Thread(new Consumer(date, 7)).start();


    }
}

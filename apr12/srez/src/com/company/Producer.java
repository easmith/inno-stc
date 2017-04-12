package com.company;

/**
 * Created by eku-win on 12.04.2017.
 */
public class Producer implements Runnable {
    private MyNumber monitor;

    public Producer(MyNumber monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                if (monitor.numbers.size() == 100) {
                    System.out.println("break");
                    break;
                }
                monitor.number = (int) (Math.random() * 100);
                monitor.notifyAll();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

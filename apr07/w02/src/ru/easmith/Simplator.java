package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Simplator implements Runnable {
    private Consumer consumer;
    private int number;

    public Simplator(Consumer consumer, int number) {
        this.consumer = consumer;
        this.number = number;
    }

    @Override
    public void run() {
        synchronized (consumer) {
            int result = number;
            consumer.sum(0, 0, result);
//            try {
//                consumer.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

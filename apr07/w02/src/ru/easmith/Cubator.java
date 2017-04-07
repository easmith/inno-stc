package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Cubator implements Runnable {
    private Consumer consumer;
    private int number;

    public Cubator(Consumer consumer, int number) {
        this.consumer = consumer;
        this.number = number;
    }

    @Override
    public void run() {
        synchronized (consumer) {
            int result = number * number * number;
            consumer.sum(result, 0, 0);
            try {
                consumer.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

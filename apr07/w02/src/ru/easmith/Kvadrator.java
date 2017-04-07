package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Kvadrator implements Runnable {
    private Consumer consumer;
    private int number;

    public Kvadrator(Consumer consumer, int number) {
        this.consumer = consumer;
        this.number = number;
    }

    @Override
    public void run() {
        synchronized (consumer) {
            int result = number * number;
            consumer.sum(0, result, 0);
            try {
                consumer.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

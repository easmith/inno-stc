package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Cubator implements Runnable {
    private int number;

    public Cubator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int result = number * number * number;
    }
}

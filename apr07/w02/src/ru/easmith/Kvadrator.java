package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Kvadrator implements Runnable {
    private int number;

    public Kvadrator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int result = number;
    }
}

package ru.easmith.observer;

/**
 * Created by eku on 04.05.17.
 */
public class Main {
    public static void main(String[] args) {
        HR hr = new HR();

        hr.registerObserver(new Worker(1));
        hr.registerObserver(new Worker(2));
        hr.registerObserver(new Worker(3));

        hr.notifyAllObservers();
    }
}

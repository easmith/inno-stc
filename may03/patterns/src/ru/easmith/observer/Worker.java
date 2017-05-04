package ru.easmith.observer;

/**
 * Created by eku on 04.05.17.
 */
public class Worker implements Observer {
    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public void message(String message) {
        System.out.println("worker id " +id + " receive: " + message);
    }
}

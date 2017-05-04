package ru.easmith.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 04.05.17.
 */
public class HR implements Observable {

    List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer :
                observers) {
            observer.message("you have message");
        }
    }
}

package ru.easmith.observer;

/**
 * Created by eku on 04.05.17.
 */
public interface Observable {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObservers();
}

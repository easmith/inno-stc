package com.company;

/**
 * Created by eku on 04.04.17.
 */
public class Calc<T extends Number> {
    int sum(T a, T b) {
        return a.intValue() + b.intValue();
    }

    public <T extends Comparable> void asd() {

    }

}

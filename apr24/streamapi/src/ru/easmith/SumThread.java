package ru.easmith;

import java.util.concurrent.Callable;

/**
 * Created by eku on 24.04.17.
 */
public class SumThread implements Callable {
    private String fileName;

    public SumThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() {
        return 3;
    }
}

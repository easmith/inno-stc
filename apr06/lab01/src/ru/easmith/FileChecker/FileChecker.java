package ru.easmith.FileChecker;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Callable {
    private static WordSet<String> wordSet = new WordSet<>();
    private String resourceName;
    private Lock lockerFromMain;

    public FileChecker(String resourceName, Lock lockerFromMain) {
        this.resourceName = resourceName;
        this.lockerFromMain = lockerFromMain;
    }

    @Override
    public Result call() {
        return ResourceParser.parse(this.resourceName, wordSet, lockerFromMain);
    }
}

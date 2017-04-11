package ru.easmith.FileChecker;

import java.util.concurrent.Callable;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Callable {
    private static ConcurrentWordSet<String> wordSet = new ConcurrentWordSet<>();
    private String resourceName;

    public FileChecker(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public Result call() {
        return ResourceParser.parse(this.resourceName, wordSet);
    }
}

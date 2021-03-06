package ru.easmith.FileChecker;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Runnable {
    private static WordSet<String> wordSet = new WordSet<>();
    private String resourceName;
    private FileCheckerPool pool;

    public FileChecker(String resourceName, FileCheckerPool pool) {
        this.resourceName = resourceName;
        this.pool = pool;
    }

    @Override
    public void run() {
        Result result = ResourceParser.parse(this.resourceName, wordSet);
//        System.out.println(result);
        pool.setComplete(result);
    }
}

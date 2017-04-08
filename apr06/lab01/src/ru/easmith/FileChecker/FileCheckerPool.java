package ru.easmith.FileChecker;

/**
 * Created by eku on 08.04.17.
 */
public class FileCheckerPool {
    volatile static int inPool = 0;
    public static String[] resources;
    public static String[] status = { "неудалось открыть", "нерусское слово", "дубликат", "остановился на слове", "последнее слово"};

    public FileCheckerPool(String[] resources) {
        this.resources = resources;
        this.inPool = resources.length;
    }

    public void startCheck() {
        for (String fName :
                resources) {
            new Thread(new FileChecker(fName, this)).start();
        }
        while (!this.isComplete()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isComplete() {
        return inPool == 0;
    }

    public void setComplete(String resourceName, int result, String word) {
        this.inPool--;
        System.out.println(resourceName + ": " + status[result + 3] + " " + (result > -3 ? word : ""));
        this.notify();
    }
}

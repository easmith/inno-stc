package ru.easmith.FileChecker;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Runnable {
    private String resourceName;
    private FileCheckerPool pool;
    private WordSet<String> buffer;
    private String word;

    public FileChecker(String resourceName, FileCheckerPool pool) {
        this.resourceName = resourceName;
        this.pool = pool;
        this.buffer = WordSet.getInstance();
    }

    /**
     * Проверяет файл на соответствие правилу - неповторяющиеся русские слова
     *
     * @param resourceName Имя ресурса
     * @return
     */
    public int isValid(String resourceName) {
        InputStream source = null;
        try {
            source = new URL(resourceName).openStream();
        } catch (IOException e) {
            try {
                source = new FileInputStream(resourceName);
            } catch (IOException e1) {
                return -3;
            }
        }

        try (Scanner sc = new Scanner(source)) {
            String delimiter = "[ \n]+";
            wordByWordCycle:
            while (sc.useDelimiter(delimiter).hasNext()) {
                if (this.buffer.isDuplicateFound) {
                    return 0;
                }
                word = sc.useDelimiter(delimiter).next();

                if (!word.matches("^[А-яёЁ]+$")) {
                    return -2;
                }
                synchronized (this.buffer) {
                    if (this.buffer.contains(word)) {
                        this.buffer.isDuplicateFound = true;
                        return -1;
                    }
                    this.buffer.add(word);
                }
            }
        } catch (NullPointerException e) {
            return -3;
        }
        return 1;
    }

    @Override
    public synchronized void run() {
        synchronized (this.buffer) {
            this.buffer.getResources().put(this.resourceName, 0);
        }
        int result = isValid(this.resourceName);
        synchronized (pool) {
            pool.setComplete(resourceName, result, word);
        }
        synchronized (this.buffer) {
            this.buffer.getResources().put(this.resourceName, 1);
        }
    }
}

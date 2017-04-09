package ru.easmith.FileChecker;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Callable {
    private String resourceName;
    private WordSet<String> wordSet;
    private String word;

    public FileChecker(String resourceName) {
        this.resourceName = resourceName;
        this.wordSet = WordSet.getInstance();
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
                if (this.wordSet.isDuplicateFound) {
                    return 0;
                }
                word = sc.useDelimiter(delimiter).next();

                if (!word.matches("^[А-яёЁ]+$")) {
                    return -2;
                }
                synchronized (this.wordSet) {
                    if (this.wordSet.contains(word)) {
                        this.wordSet.isDuplicateFound = true;
                        return -1;
                    }
                    this.wordSet.add(word);
                }
            }
        } catch (NullPointerException e) {
            return -3;
        }
        return 1;
    }

    public Result call() {
        Result result = new Result();
        result.resourceName = this.resourceName;
        result.result = isValid(this.resourceName);
        result.word = this.word;
        return result;
    }
}

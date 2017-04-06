package ru.easmith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by eku on 06.04.17.
 */
public class FileChecker implements Runnable {
    private String resourceName;

    private WordBuffer<String> buffer;

    public FileChecker(String resourceName, WordBuffer<String> buffer) {
        this.resourceName = resourceName;
        this.buffer = buffer;
    }

    public boolean isValid(String fileName) {

        try (Scanner sc = new Scanner(new File(fileName))) {
            String delimiter = "[ \n]+";
            wordByWordCycle: while (sc.useDelimiter(delimiter).hasNext()) {
                synchronized (buffer) {
                    if (!buffer.isActive()) {
                        return false;
                    }
                    String word = sc.useDelimiter(delimiter).next();
                    if (!word.matches("^[А-яёЁ]+$")) {
                        System.out.println(fileName + ": Слово #" + buffer.size() + " нерусское: " + word);
                        buffer.setIsActive(false);
                        return false;
                    }
                    if (buffer.contains(word)) {
                        System.out.println(fileName + ": Слово #" + buffer.size() + " '" + word + "' является дубликатом");
                        buffer.setIsActive(false);
                        return false;
                    }
                    buffer.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public synchronized void run() {
        synchronized (buffer) {
            buffer.getResources().put(resourceName, 0);
        }
        if (isValid(resourceName)) {
            System.out.println(resourceName + " не содержит дубликатов");
        };
        synchronized (buffer) {
            buffer.getResources().put(resourceName, 1);
        }
    }
}

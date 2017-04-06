package ru.easmith.FileChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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

    /**
     * Проверяет файл на соответствие правилу - неповторяющиеся русские слова
     * @param resourceName Имя ресурса
     * @return
     */
    public boolean isValid(String resourceName) {
        try (Scanner sc = new Scanner(new File(resourceName))) {
            String delimiter = "[ \n]+";
            wordByWordCycle: while (sc.useDelimiter(delimiter).hasNext()) {
                synchronized (this.buffer) {
                    if (!this.buffer.isActive()) {
                        return false;
                    }
                    String word = sc.useDelimiter(delimiter).next();
                    if (!word.matches("^[А-яёЁ]+$")) {
                        System.out.println(resourceName + ": Слово #" + this.buffer.size() + " нерусское: " + word);
                        this.buffer.setIsActive(false);
                        return false;
                    }
                    if (this.buffer.contains(word)) {
                        System.out.println(resourceName + ": Слово #" + this.buffer.size() + " '" + word + "' является дубликатом");
                        this.buffer.setIsActive(false);
                        return false;
                    }
                    this.buffer.add(word);
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
        synchronized (this.buffer) {
            this.buffer.getResources().put(this.resourceName, 0);
        }
        if (isValid(this.resourceName)) {
            System.out.println(this.resourceName + " не содержит дубликатов");
        };
        synchronized (this.buffer) {
            this.buffer.getResources().put(this.resourceName, 1);
        }
    }
}

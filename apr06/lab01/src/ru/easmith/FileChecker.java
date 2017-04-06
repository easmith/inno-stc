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

    public FileChecker(String resourceName) {
        this.resourceName = resourceName;
    }

    public static void check(String fileName) {
        TreeSet<String> buffer = new TreeSet<>();

        try (Scanner sc = new Scanner(new File(fileName))) {
            String delimiter = "[ \n]+";
            while (sc.useDelimiter(delimiter).hasNext()) {
                String word = sc.useDelimiter(delimiter).next();
                if (!word.matches("^[А-яёЁ]+$")) {
                    System.out.println("Слово #" + buffer.size() + " нерусское: " + word + " " + buffer.size());
                    return;
                }
                if (buffer.contains(word)) {
                    System.out.println("Дубликат в \"" + fileName + "\": " + word);
                    System.out.println("Проверено слов: " + buffer.size());
                    return;
                }
                buffer.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Start worker for " + resourceName);
        check(resourceName);
    }
}

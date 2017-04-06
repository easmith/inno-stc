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
            String delimiter = "[A-z0-9 .,\n\r?]+";
            while (sc.useDelimiter(delimiter).hasNext()) {
                String word = sc.useDelimiter(delimiter).next();
                if (buffer.contains(word)) {
                    System.out.println(word);
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
        check(resourceName);
    }
}

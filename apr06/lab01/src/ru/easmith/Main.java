package ru.easmith;

import ru.easmith.FileChecker.FileCheckerPool;
import ru.easmith.FileChecker.ResourceStream;
import sun.net.util.URLUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    static String s;

    public static void main(String[] args) {
        int resourcesCount = 100;
        String[] resources = new String[resourcesCount];
        for (int i = 0; i < resourcesCount; i++) {
            resources[i] = "texts/file" + i + ".txt";
//            fileGenerator(resources[i], 10000);
        }

        System.out.println("Waiting for start...");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("Start!");

        // время для контроля производительности
        long startDate = System.currentTimeMillis();

        FileCheckerPool filecheckerPool = new FileCheckerPool(resources);
        filecheckerPool.startCheck();

        System.out.println("Потребовалось времени: " + (System.currentTimeMillis() - startDate) + " мс");

        System.out.println("All thread are started");
        scanner.next();
    }

    public  void print(Object o) {
        System.out.println("Obj");
    }

    public  void print(String s) {
        System.out.println("asd");

    }

    /**
     * @param name   Имя файла
     * @param number Количество слов
     */
    public static void fileGenerator(String name, int number) {
        String symbols = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        symbols += "абвгдеёжзиклмнопрстуфхцчшщъыьэюя";
        // для случайного нерусского символа
        // 0.4 - вероятность, что файл будет содержать хоть одно слово с символом на латинице
//        symbols += Math.random() < .1 ? "Z" : "";

        try (FileOutputStream fos = new FileOutputStream(name)) {
            for (int i = 0; i < number; i++) {
                String word = "";
                int wordLen = (int) (Math.random() * 3 + 7);
                for (int j = 0; j < wordLen; j++) {
                    word += symbols.charAt((int) (Math.random() * symbols.length()));
                }
                word += Math.random() < .05 ? "\n" : " ";
                fos.write(word.getBytes("UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

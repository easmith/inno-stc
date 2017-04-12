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


public class Main {
    static String s;

    public static void main(String[] args) {
        Formatter formatter = new Formatter(Locale.ROOT);
        formatter.format("%.2E\n", 100.0/3.0);      //1
        formatter.format("%.2f", 100.0/3.0);        //2
        System.out.println(formatter);

//        int resourcesCount = 100;
//        String[] resources = new String[resourcesCount];
//        for (int i = 0; i < resourcesCount; i++) {
//            resources[i] = "texts/file" + i + ".txt";
////            fileGenerator(resources[i], 10000);
//        }
//
//        // время для контроля производительности
//        long startDate = System.currentTimeMillis();
//
//        FileCheckerPool filecheckerPool = new FileCheckerPool(resources);
//        filecheckerPool.startCheck();
//
//        System.out.println("Потребовалось времени: " + (System.currentTimeMillis() - startDate) + " мс");
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

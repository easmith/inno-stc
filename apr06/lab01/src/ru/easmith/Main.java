package ru.easmith;

import ru.easmith.FileChecker.FileChecker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    public static void main(String[] args) {

        int resourcesCount = 100;
        String[] resources = new String[resourcesCount];
        for (int i = 0; i < resourcesCount; i++) {
            resources[i] = "texts/file" + i + ".txt";
//            fileGenerator(resources[i], 10000);
        }

        // время для контроля производительности
        long startDate = System.currentTimeMillis();

        ArrayList<Future> futures = new ArrayList<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ReentrantLock locker = new ReentrantLock();
        for (String resource :
                resources) {
            futures.add(threadPool.submit(new FileChecker(resource, locker)));
        }

        for (Future future :
                futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();

        System.out.println("Потребовалось времени: " + (System.currentTimeMillis() - startDate) + " мс");
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

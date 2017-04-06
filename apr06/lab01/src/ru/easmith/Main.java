package ru.easmith;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] resources = {"genText1.txt", "genText2.txt", "genText3.txt"};

        for (String resource :
                resources) {
            fileGenerator(resource, 10000);
        }

        WordBuffer<String> buffer = new WordBuffer<>();

        for (String fName :
                resources) {
            new Thread(new FileChecker(fName, buffer)).start();
        }

        synchronized (buffer) {
            buffer.notifyAll();
        }

        while (buffer.isActive()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (buffer) {
                System.out.println("Проверено слов: " + buffer.size());
                int sum = 0;
                for(Map.Entry<String, Integer> entry : buffer.getResources().entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    sum += value;
                }
                if (sum == buffer.getResources().size()) {
                    break;
                }
            }
        }
    }

    public static void fileGenerator(String name, int number) {
        String symbols = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        symbols += "абвгдеёжзиклмнопрстуфхцчшщъыьэюя";
        // для случайного нерусского символа
        // 0.4 - вероятность, что файл будет содержать хоть одно слово с символом на латинице
//        symbols += Math.random() < .1 ? "Z" : "";

        try (FileOutputStream fos = new FileOutputStream(name)) {
            for (int i = 0; i < number; i++) {
                String word = "";
                int wordLen = (int) (Math.random() * 3 + 4);
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

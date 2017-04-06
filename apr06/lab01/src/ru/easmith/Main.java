package ru.easmith;

import ru.easmith.FileChecker.FileChecker;
import ru.easmith.FileChecker.WordBuffer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        String[] resources = {"genText1.txt", "genText2.txt", "genText3.txt"};

        // заполняем файлы случайными "словами"
        for (String resource :
                resources) {
            fileGenerator(resource, 10000);
        }

        for (String fName :
                resources) {
            new Thread(new FileChecker(fName)).start();
        }

        WordBuffer buffer = WordBuffer.getInstance();

        synchronized (buffer) {
            buffer.notifyAll();
        }

        while (buffer.isActive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (buffer) {
                System.out.println("Проверено слов: " + buffer.size());
                int sum = 0;

                for (String res :
                        resources) {
                    sum += (int) buffer.getResources().get(res);
                }

                if (sum == buffer.getResources().size()) {
                    break;
                }
            }
        }
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

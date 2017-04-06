package ru.easmith;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String[] resources = {"generatedText1.txt", "generatedText2.txt", "generatedText3.txt", "generatedText4.txt", "generatedText5.txt"};

        for (String resource :
                resources) {
            fileGenerator(resource, 10000);
        }

        for (String fName :
                resources) {
            new Thread(new FileChecker(fName)).start();
        }
    }


    public static void fileGenerator(String name, int number) {
        String symbols = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        symbols += "абвгдеёжзиклмнопрстуфхцчшщъыьэюя";
        // для случайного нерусского символа
        // 0.4 - вероятность, что файл будет содержать хоть одно слово с символом на латинице
        symbols += Math.random() < .4 ? "Z" : "\n";

        try (FileOutputStream fos = new FileOutputStream(name)) {
            for (int i = 0; i < number; i++) {
                String word = "";
                int wordLen = (int) (Math.random() * 3 + 3);
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

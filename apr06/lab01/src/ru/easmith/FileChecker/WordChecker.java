package ru.easmith.FileChecker;

/**
 * Created by eku-win on 10.04.2017.
 */
public class WordChecker {

    public static boolean isValid(String word) {
        return  word.matches("^[А-яёЁ]+$");
    }
}

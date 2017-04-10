package ru.easmith.FileChecker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by eku-win on 10.04.2017.
 */
public class ResourceParser {

    /**
     * Проверяет файл на соответствие правилу - неповторяющиеся русские слова
     *
     * @param resourceName Название ресурса
     * @param wordSet Набор вхождений
     * @return
     */
    public static Result parse(String resourceName, WordSet wordSet) {
        String word = new String();
        InputStream source = null;
        try {
            source = new ResourceStream(resourceName);
        } catch (IOException e) {
            return new Result(resourceName, word, Result.Results.CANT_OPEN);
        }

        try (Scanner sc = new Scanner(source)) {
            String delimiter = "[ \n]+";
            while (sc.useDelimiter(delimiter).hasNext()) {
                if (wordSet.isDuplicateFound) {
                    return new Result(resourceName, word, Result.Results.STOP);
                }
                word = sc.useDelimiter(delimiter).next();

                if (!WordChecker.isValid(word)) {
                    return new Result(resourceName, word, Result.Results.BAD_WORD);
                }
                synchronized (wordSet) {
                    if (wordSet.contains(word)) {
                        wordSet.isDuplicateFound = true;
                        return new Result(resourceName, word, Result.Results.DUPLICATE);
                    }
                    wordSet.add(word);
                }
            }
        } catch (NullPointerException e) {
            return new Result(resourceName, word, Result.Results.CANT_OPEN);
        }
        return new Result(resourceName, word, Result.Results.OK);
    }
}

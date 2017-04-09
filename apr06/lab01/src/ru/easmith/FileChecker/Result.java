package ru.easmith.FileChecker;

/**
 * Created by eku on 09.04.17.
 */
public class Result {
    public String resourceName;
    public String word;
    public int result;
    public String[] status = { "неудалось открыть", "нерусское слово", "дубликат", "остановился на слове", "последнее слово"};

    @Override
    public String toString() {
        return resourceName + ": " + status[result + 3] + " '" + word + "'";
    }
}

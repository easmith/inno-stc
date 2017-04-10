package ru.easmith.FileChecker;

/**
 * Created by eku on 09.04.17.
 */
public class Result {
    private final Results result;
    private String resourceName;
    private String word;
    private enum Results {CANT_OPEN, BAD_WORD, DUPLICATE, STOP, OK};
    private String[] status = { "неудалось открыть", "нерусское слово", "дубликат", "остановился на слове", "последнее слово"};

    public Result(String resourceName, String word, Results result) {
        this.resourceName = resourceName;
        this.word = word;
        this.result = result;
    }

    @Override
    public String toString() {
        return resourceName + ": " + status[Results.valueOf(result.toString()).ordinal()] + " '" + word + "'";
    }
}
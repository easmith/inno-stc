package ru.easmith.FileChecker;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by eku on 06.04.17.
 */
public class WordSet<String> extends HashSet {
    static volatile boolean isDuplicateFound = false;
    private HashMap<String, Integer> resources = new HashMap<>();

    private WordSet() {
//        super(1000);
    }

    public static WordSet getInstance() {
        return WordSetHolder.instance;
    }

    public HashMap<String, Integer> getResources() {
        return this.resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    private static class WordSetHolder {
        private final static WordSet instance = new WordSet();
    }

}

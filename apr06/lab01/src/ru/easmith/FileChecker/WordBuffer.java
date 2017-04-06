package ru.easmith.FileChecker;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by eku on 06.04.17.
 */
public class WordBuffer<String> extends TreeSet {
    private boolean isActive = true;
    private HashMap<String, Integer> resources = new HashMap<>();

    private WordBuffer() {
    }

    private static class WordBufferHolder {
        private final static WordBuffer instance = new WordBuffer();
    }

    public static WordBuffer getInstance() {
        return WordBufferHolder.instance;
    }

    public HashMap<String, Integer> getResources() {
        return this.resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    /**
     * Активен ли буфер?
     * @return
     */
    public boolean isActive() {
        return this.isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

}

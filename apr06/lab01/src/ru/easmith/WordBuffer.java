package ru.easmith;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by eku on 06.04.17.
 */
public class WordBuffer<String> extends TreeSet {
    private boolean isActive = true;
    private HashMap<String, Integer> resources = new HashMap<>();

    public HashMap<String, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

}

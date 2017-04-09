package ru.easmith.FileChecker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by eku on 06.04.17.
 */
//public class WordSet<String> extends HashSet {
public class WordSet<E> extends AbstractSet<E> implements Set<E>{
    private final ConcurrentMap<E, Object> theMap;

    private static final Object dummy = new Object();

    static volatile boolean isDuplicateFound = false;
//    static Set words =
    private HashMap<String, Integer> resources = new HashMap<>();

    private WordSet() {
        this.theMap = new ConcurrentHashMap<E, Object>();
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

    @Override
    public int size() {
        return theMap.size();
    }

    @Override
    public Iterator<E> iterator(){
        return theMap.keySet().iterator();
    }

    @Override
    public boolean isEmpty(){
        return theMap.isEmpty();
    }

    @Override
    public boolean add(final E o){
        return theMap.put(o, WordSet.dummy) == null;
    }

    @Override
    public boolean contains(final Object o){
        return theMap.containsKey(o);
    }

    @Override
    public void clear(){
        theMap.clear();
    }

    @Override
    public boolean remove(final Object o){
        return theMap.remove(o) == WordSet.dummy;
    }

    public boolean addIfAbsent(final E o){
        Object obj = theMap.putIfAbsent(o, WordSet.dummy);
        return obj == null;
    }

}

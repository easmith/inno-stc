package ru.easmith.FileChecker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by eku on 06.04.17.
 */
//public class WordSet<String> extends HashSet {
public class ConcurrentWordSet<E> extends AbstractSet<E> implements Set<E>{
    static volatile boolean isDuplicateFound = false;

    private final ConcurrentMap<E, Object> theMap;

    private static final Object dummy = new Object();

    public ConcurrentWordSet() {
        this.theMap = new ConcurrentHashMap<E, Object>(100000);
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
        return theMap.put(o, ConcurrentWordSet.dummy) == null;
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
        return theMap.remove(o) == ConcurrentWordSet.dummy;
    }

    public boolean addIfAbsent(final E o){
        Object obj = theMap.putIfAbsent(o, ConcurrentWordSet.dummy);
        return obj == null;
    }

}

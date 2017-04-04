package com.company;


import java.util.List;

/**
 * Created by eku on 04.04.17.
 */
public class MyArrayList {

    public static Object arr[] = new Object[10];
    public static int current = 0;

    public <T> void add(T e) throws ArrayIndexOutOfBoundsException{
        arr[++current] = e;
    }

    public void remove(int n){
        for (int i = n; i < current; i++)
        {
            arr[i] = arr[i + 1];
        }
        arr[current] = null;
        current -= 1;
    }

    public Object next() {
        return arr[current + 1];
    }

    public Object prev() {
        return arr[current - 1];
    }

    public Object current() {
        return arr[current];
    }
}

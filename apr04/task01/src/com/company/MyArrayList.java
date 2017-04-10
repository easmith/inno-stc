package com.company;


/**
 * Created by eku on 04.04.17.
 */
public class MyArrayList<E> {

    public Object[] arr;
    public int current = 0;

    public MyArrayList() {
        arr = new Object[10];
    }

    public boolean add(E e) throws ArrayIndexOutOfBoundsException{
        arr[current++] = e;
        return true;
    }

    public void remove(int n){
        for (int i = n; i < current; i++) {
            arr[i] = arr[i + 1];
        }
        arr[current] = null;
        current -= 1;
    }

    public boolean set(int n, E e) {
        System.arraycopy(arr, n, arr, n + 1, arr.length - n - 1);
        arr[n] = e;
        return true;
    }

    public E get(int n) {
        return (E)arr[n];
    }

    public Object current() {
        return arr[current];
    }
}

package com.company;

public class Main {

    public static void main(String[] args) {
        MyNumber monitor = new MyNumber();

        new Thread(new Producer(monitor)).start();
        new Thread(new Consumer(monitor)).start();
    }
}

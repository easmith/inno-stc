package ru.easmith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("Start!");
        Random random = new Random();
        for (int i = 0; i < 10_000; i++) {
            Thread t;
            switch (random.nextInt(5)) {
                case 0:
                    t = new Thread(new Runnable1(i));
                    break;
                case 1:
                    t = new Thread(new Runnable1(i));
                    break;
                case 2:
                    t = new Thread(new Runnable1(i));
                    break;
                case 3:
                    t = new Thread(new Runnable1(i));
                    break;
                default:
                    t = new Thread(new Runnable1(i));
                    break;
            }
            t.setName("Thread " + i);
            t.start();
        }
        System.out.println("All thread are started");
        scanner.next();

    }
}

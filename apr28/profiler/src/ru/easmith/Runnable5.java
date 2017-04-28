package ru.easmith;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by eku on 28.04.17.
 */
public class Runnable5 implements Runnable {
    public int i;

    public Runnable5(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        final int j = i;
        System.out.println(j);
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<>();
        for (int k = 0; k < random.nextInt(1_000); k++) {
            list.add(k + "Thread of " + j);
        }
        System.out.println("Thread N " + j + " is finished.");
    }
}

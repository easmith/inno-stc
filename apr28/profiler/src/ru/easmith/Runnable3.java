package ru.easmith;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by eku on 28.04.17.
 */
public class Runnable3 implements Runnable {
    public int i;

    public Runnable3(int i) {
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
        saveToFile(list.stream().reduce((s1, s2) -> s1 + s2).toString());
    }

    private void saveToFile(String string) {
        try {
            FileWriter fileWriter = new FileWriter("dump3.txt");
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

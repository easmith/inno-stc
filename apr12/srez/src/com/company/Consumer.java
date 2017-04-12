package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by eku-win on 12.04.2017.
 */
public class Consumer implements Runnable {
    protected MyNumber monitor;
    public Consumer(MyNumber monitor) {
        this.monitor = monitor;
    }

    public void putNumber (int i, int number) {
        monitor.numbers.put(number, true);
        if (i % 5 == 0) {
            for (Entry entry:
                    monitor.numbers.entrySet()) {
                System.out.println(entry.getKey() + "=>" + entry.getValue());
            }
            System.out.println("SIZE: " + monitor.numbers.size());
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.putNumber(i, monitor.number);
                if (monitor.numbers.size() == 100) {
                    break;
                }
            }
            i++;
        }
    }
}

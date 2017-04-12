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
                monitor.numbers.put(monitor.number, true);
                if (i % 5 == 0) {
                    for (Entry entry:
                            monitor.numbers.entrySet()) {
                        System.out.println(entry.getKey() + "=>" + entry.getValue());
                    }
                    System.out.println("SIZE: " + monitor.numbers.size());
                }
                if (monitor.numbers.size() == 100) {
                    break;
                }
            }
        }
    }
}

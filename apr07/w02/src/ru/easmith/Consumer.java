package ru.easmith;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by eku on 07.04.17.
 */
public class Consumer {

    private Object cubator = new Object();
    private Object kvadrator = new Object();
    private Object simplator = new Object();

    public void sum(int resCubator, int resKvadrator, int resSimplator) {
        System.out.println("invoke " + resCubator + " " + resKvadrator + " " + resSimplator);

        int result = 0;

        if (resCubator > 0) {
            synchronized (cubator) {
                result += resCubator;
            }
        }

        if (resKvadrator > 0) {
            synchronized (kvadrator) {
                result += resKvadrator;
            }
        }

        if (resSimplator > 0) {
            synchronized (simplator) {
                result += resSimplator;
            }
        }

        System.out.println(result);
    }
}

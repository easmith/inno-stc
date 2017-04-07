package ru.easmith;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by eku on 07.04.17.
 */
public class Consumer {

    private Stack<Integer> cubator = new Stack<>();
    private Stack<Integer> kvadrator = new Stack<>();
    private Stack<Integer> simplator = new Stack<>();

    public void sum(int resCubator, int resKvadrator, int resSimplator) {
        System.out.println("invoke " + resCubator + " " + resKvadrator + " " + resSimplator);
        synchronized (this) {
            if (resCubator > 0) {
                cubator.push(resCubator);
            }
            if (resKvadrator > 0) {
                kvadrator.push(resKvadrator);
            }
            if (resSimplator > 0) {
                simplator.push(resSimplator);
            }
            while (!cubator.empty() && !kvadrator.empty() && !simplator.empty()) {
                int kva = kvadrator.pop();
                int cub = cubator.pop();
                int sim = simplator.pop();
                System.out.println("ConsumerSum: " + cub + " + " + kva + " + " + sim + " = " + (cub + kva + sim));
            }
        }
    }
}

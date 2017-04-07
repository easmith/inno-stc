package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class Consumer{

    private int cubator = 0;
    private int kvadrator = 0;
    private int simplator = 0;

    public void sum(int resCubator, int resKvadrator, int resSimplator) {
        if (resCubator > 0) {
            cubator = resCubator;
        }
        if (resKvadrator > 0) {
            kvadrator = resKvadrator;
        }
        if (resSimplator > 0) {
            simplator = resSimplator;
        }
        if (cubator > 0 && kvadrator > 0 && simplator > 0) {
            System.out.println("ConsumerSum: " + cubator + " + " + kvadrator + " + " + simplator + " = " + (cubator + kvadrator + simplator));
            cubator = 0;
            kvadrator = 0;
            simplator = 0;
        }
    }
}

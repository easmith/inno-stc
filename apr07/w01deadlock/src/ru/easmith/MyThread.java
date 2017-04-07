package ru.easmith;

/**
 * Created by eku on 07.04.17.
 */
public class MyThread implements Runnable {
    private Object obj1;
    private Object obj2;

    public MyThread(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            System.out.println(this.hashCode() + " In sync " + obj1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (obj2) {
                System.out.println(this.hashCode() + " In sync " + obj2);
            }
        }
    }
}

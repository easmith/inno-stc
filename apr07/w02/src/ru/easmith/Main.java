package ru.easmith;

public class Main {

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {1, 2, 3};

        Consumer counsumer = new Consumer();

        for (int i = 0; i < a.length; i++) {
            new Thread(new Cubator(counsumer, a[i])).start();
        }

        for (int i = 0; i < b.length; i++) {
            new Thread(new Kvadrator(counsumer, b[i])).start();
        }

        for (int i = 0; i < c.length; i++) {
            new Thread(new Simplator(counsumer, c[i])).start();
        }


    }
}

package com.company;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        new ArrayList();
        MyArrayList<Integer> mlst = new MyArrayList<>();

        try {
            mlst.add(0);
            mlst.add(1);
            mlst.add(2);
            mlst.add(3);
            mlst.add(4);
            mlst.add(5);
            mlst.add(6);
            mlst.add(7);
            mlst.add(8);
            mlst.add(9);
            mlst.set(3, 99);
            System.out.println(mlst.get(2));
            System.out.println(mlst.get(3));
            System.out.println(mlst.get(4));
            System.out.println(mlst.get(9));


        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Too many indexes");
        }
    }
}

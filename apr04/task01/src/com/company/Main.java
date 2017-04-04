package com.company;

public class Main {


    public static void main(String[] args) {
        MyArrayList mlst = new MyArrayList();

        try {
            mlst.add(1);
            mlst.add(2);
            mlst.add("three");
            mlst.add(4);
            mlst.add(5);
            mlst.add(6);
            mlst.add(7);
            mlst.add(8);
            mlst.add(9);
            mlst.remove(3);
            mlst.add(10);

            System.out.println(mlst.current());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Too many indexes");
        }
    }
}

package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Calc<Double> calc = new Calc<>();
        System.out.println(calc.sum(1.0,2D));
//        Cals<String> calc2 = new Calc<>();
//        calc.sum('1', 2.0);

        sout(1);
        sout("1");

//        comp(1, '2');
//        compGen(1, '2');


    }

    public static <T> void sout(T a) {
        System.out.println(a);
    }

    public static  String comp(Comparable a, Comparable b) {
        return a.compareTo(b) > 0 ? "second param grater" : "first param grater";
    }
//
//    public static <A extends Comparable<A>, B extends Comparable> String compGen(A a, B b) {
//        return a.compareTo(b) > 0 ? "second param grater" : "first param grater";
//    }
}

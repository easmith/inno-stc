package ru.easmith;

public class Main {

    public static void main(String[] args) {
	    String obj1 = new String("one");
		String obj2 = new String("two");

	    new Thread(new MyThread(obj1, obj2)).start();
	    new Thread(new MyThread(obj2, obj1)).start();

    }
}

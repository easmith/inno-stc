package ru.easmith;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        readFile();
//	    readWithTry();
        readMyFile();
    }

    public static void readFile() {
        File file = new File("input.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            System.out.println(reader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readWithTry() {
        File file = new File("input.txt");
//        BufferedReader reader = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            System.out.println("Reader");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMyFile() throws FileNotFoundException {
        try (MyFile file = new MyFile()) {
            file.doSome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

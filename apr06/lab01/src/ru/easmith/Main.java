package ru.easmith;

public class Main {

    public static void main(String[] args) {
        String[] resources = {"file1.txt", "file2.txt"};

        for (String fName :
                resources) {
            new Thread(new FileChecker(fName)).start();
        }
    }
}

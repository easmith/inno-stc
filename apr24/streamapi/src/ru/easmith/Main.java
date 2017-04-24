package ru.easmith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] resources = {"file01.txt", "file02.txt", "file03.txt"};

//        ArrayList<Thread> threads = new ArrayList<>();
//        for (String resource:
//            resources) {
//            threads.add(new Thread(new SumThread(resource)));
//        }


        Arrays.stream(resources).map((resource)->new File(resource))
                .map((resourceFile) -> {
                    try {
                        return new Scanner(resourceFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .map((scanner)-> {
                            ArrayList list = new ArrayList();
                            while (scanner.hasNext()) {
                                list.add(scanner.next());
                            }
                            return list.stream();
                });

//            Thread sumThread = new Thread(new SumThread(resource));
//            sumThread.start();
//        });
    }
}

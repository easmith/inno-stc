package ru.easmith;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by eku on 04.04.17.
 */
public class MyFile implements AutoCloseable {

    public MyFile() throws FileNotFoundException {
//        throw new FileNotFoundException();
    }

    public void doSome() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    @Override
    public void close() throws IOException {
        System.out.println("i closed file");
    }
}

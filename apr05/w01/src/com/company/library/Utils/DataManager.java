package com.company.library.Utils;

import com.company.library.models.Book;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eku on 05.04.17.
 */
public class DataManager {

    public static void serializeToFile(Set<Book> books) {

        try (FileOutputStream fos = new FileOutputStream("books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeInt(books.size());
            for (Book book: books)
                book.writeExternal(oos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Book> deserialize() {
        Set<Book> books = new HashSet<>();
        try (FileInputStream fis = new FileInputStream("books.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            int total = ois.readInt();
            System.out.println("Total "  + total);

            for (int i = 0; i < total; i++) {
                Book book = new Book();
                book.readExternal(ois);
                books.add(book);
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return books;
        }
    }
}

package com.company.library.Utils;

import com.company.library.models.Book;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eku on 05.04.17.
 */
public class DataManager {

    public static void serializeBook(Set<Book> books) {

        try (FileOutputStream fos = new FileOutputStream("books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeInt(books.size());
            for (Book book: books)
                book.writeExternal(oos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Book> deserializeBook() {
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

    public static <T extends Externalizable> void uSerialize(Set<T> objects) {
        try (FileOutputStream fos = new FileOutputStream("ubooks.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeInt(objects.size());
            for (T obj: objects)
                obj.writeExternal(oos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends Externalizable> Set<T> uDeserialize () {
        Set<T> objects = new HashSet<>();

        try (FileInputStream fis = new FileInputStream("ubooks.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            int total = ois.readInt();
            System.out.println("Total "  + total);

            for (int i = 0; i < total; i++) {
                T obj = (T)new Object();
                obj.readExternal(ois);
                objects.add(obj);
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return objects;
        }
    }
}

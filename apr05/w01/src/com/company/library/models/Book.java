package com.company.library.models;

import java.io.*;

/**
 * Created by eku on 05.04.17.
 */
public class Book implements Serializable, Externalizable {

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    public String myname;

    public String author;

    public String getTitle() {
        return title;
    }

    private String title;
    private int year;
    private String isbn;
    private static long serializeVersionUID = 2L;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Book)) {
            return false;
        }

        if (this.isbn.equals(((Book) obj).isbn)) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return author + '@' + title + '@' + year + '@' + isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF("Eugene Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        myname = in.readUTF();
    }
}

package com.company.library.models;

import java.io.*;

/**
 * Created by eku on 05.04.17.
 */
public class Book extends Model {

    private static long serialVersionUID = 2L;

    public String author;
    private String title;
    private int year;
    private String isbn;

    public Book() {
        this.author = null;
        this.title = null;
        this.year = 0;
        this.isbn = null;
    }

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

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
        return author + ' ' + title;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.author);
        out.writeUTF(this.title);
        out.writeInt(this.year);
        out.writeUTF(this.isbn);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.author = (String) in.readUTF();
        this.title = (String) in.readUTF();
        this.year = in.readInt();
        this.isbn = (String) in.readUTF();
        super.readExternal(in);
    }
}

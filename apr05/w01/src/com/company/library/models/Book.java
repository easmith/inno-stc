package com.company.library.models;

/**
 * Created by eku on 05.04.17.
 */
public class Book {
    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    public String author;
    public String title;
    public int year;
    public String isbn;

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
}

package com.company.library.models;

import java.util.List;
import java.util.UUID;

/**
 * Created by eku on 05.04.17.
 */
public class BookInstance {
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private Book book;
    private UUID number;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;

    }

    @Override
    public int hashCode() {
        return number.hashCode() * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        if (!this.number.equals(((BookInstance) obj).number)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "[" + book + "] #" + number;
    }
}

package com.company.library.models;

import java.util.List;

/**
 * Created by eku on 05.04.17.
 */
public class BookInstance {
    private Book book;
    private List<Reader> readers;

    private int number;

    private List<Booking> bookInstance;

    public BookInstance(Book book, int number) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        return number * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        if (number != ((BookInstance) obj).number) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return book + "@" + number;
    }
}

package com.company.library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.UUID;

/**
 * Created by eku on 05.04.17.
 */
public class BookInstance implements Externalizable {
    private Book book;
    private UUID number;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.book);
        out.writeObject(this.number);
        out.writeUTF("Evgeny Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.book = (Book) in.readObject();
        this.number = (UUID) in.readObject();
        in.readUTF();
    }
}

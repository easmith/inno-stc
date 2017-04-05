package com.company.library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;
import java.util.UUID;

/**
 * Created by eku on 05.04.17.
 */
public class Booking implements Externalizable {
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date returnDate;
    private Date finishDate;
    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date returnDate) {
        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public int hashCode() {
        return bookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Booking))
            return false;

        if (!(bookInstance.equals(((Booking) obj).bookInstance)
                && reader.equals(((Booking) obj).reader)
                && startDate.equals(((Booking) obj).startDate))) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + reader + "] read [" + bookInstance.getBook() + "]";
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.bookInstance);
        out.writeObject(this.reader);
        out.writeObject(this.startDate);
        out.writeObject(this.finishDate);
        out.writeObject(this.returnDate);
        out.writeUTF("Evgeny Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.bookInstance = (BookInstance) in.readObject();
        this.reader = (Reader) in.readObject();
        this.startDate = (Date) in.readObject();
        this.finishDate = (Date) in.readObject();
        this.returnDate = (Date) in.readObject();
        in.readUTF();
    }
}

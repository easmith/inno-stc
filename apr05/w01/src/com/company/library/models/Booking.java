package com.company.library.models;

import java.util.Date;

/**
 * Created by eku on 05.04.17.
 */
public class Booking {
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date returnDate;
    private Date finishDate;

    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date returnDate, Date finishDate) {
        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.returnDate = returnDate;
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
        return "Booking{" +
                "bookInstance=" + bookInstance +
                ", reader=" + reader +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", finishDate=" + finishDate +
                '}';
    }
}

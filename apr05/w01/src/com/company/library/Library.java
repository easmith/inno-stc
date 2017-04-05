package com.company.library;

import com.company.library.Utils.DataManager;
import com.company.library.models.Book;
import com.company.library.models.BookInstance;
import com.company.library.models.Booking;
import com.company.library.models.Reader;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * Created by eku on 05.04.17.
 */
public class Library {
    private Set<Book> books;
    private Set<BookInstance> bookInstances;
    private Set<Booking> bookings;
    private Set<Reader> readers;

    public Library() {
        books = new HashSet<>(1024);
        bookInstances = new HashSet<>(4096);
        readers = new HashSet<>(512);
        bookings = new HashSet<>(2048);
    }

    public Set<BookInstance> getBookInstances() {
        return bookInstances;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void buyBook(Book book, int quantity) {
        books.add(book);
        for (int i = 0; i < quantity; i++) {
            BookInstance bookInstance = new BookInstance(book, UUID.randomUUID());
            bookInstances.add(bookInstance);
        }
    }

    public void takeBook(Reader reader, Book book) {
        BookInstance bookInstance = null;

        for (BookInstance forBookInstance :
                bookInstances) {
            if (forBookInstance.getBook().getIsbn() == book.getIsbn()) {
                bookInstances.remove(forBookInstance);
                bookings.add(new Booking(forBookInstance, reader, new Date(), new Date()));
                bookInstance = forBookInstance;
                break;
            }
        }

        if (bookInstance == null) {
            System.out.println("Unknown book '" + book.getTitle() + "'");
        }

        readers.add(reader);
    }

    public void returnBook(Reader reader, Book book) {
        Booking booking = null;

        for (Booking forBooking :
                bookings) {
            if (forBooking.getBookInstance().getBook().getIsbn() == book.getIsbn()) {
                bookings.remove(forBooking);
                bookInstances.add(forBooking.getBookInstance());
                booking = forBooking;
                break;
            }
        }

        if (booking == null) {
            System.out.println("Wow new book?");
        }
    }

    public void showAllData() {
        System.out.println("\nLibrary:\nBooks:");
        for (Book book :
                books) {
            System.out.println("\t" + book);
        }

        System.out.println("BookInstance:");
        for (BookInstance bookInstance :
                bookInstances) {
            System.out.println("\t" + bookInstance);
        }

        System.out.println("Readers:");
        for (Reader reader :
                readers) {
            System.out.println("\t" + reader);
        }

        System.out.println("Bookings:");
        for (Booking booking :
                bookings) {
            System.out.println("\t" + booking);
        }
    }

    public void serialize() {
        DataManager.uSerialize(books);
        DataManager.uSerialize(bookInstances);
        DataManager.uSerialize(bookings);
        DataManager.uSerialize(readers);
    }

    public static Library unserialize() {
        Library library = new Library();
        library.books = DataManager.uDeserialize(new Book());
        library.bookInstances = DataManager.uDeserialize(new BookInstance());
        library.bookings = DataManager.uDeserialize(new Booking());
        library.readers = DataManager.uDeserialize(new Reader());

        return library;
    }
}

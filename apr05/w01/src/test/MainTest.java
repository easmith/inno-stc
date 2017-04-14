package test;

import com.company.library.Library;
import com.company.library.models.Book;
import com.company.library.models.BookInstance;
import com.company.library.models.Booking;
import com.company.library.models.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static Library library;

    @BeforeAll
    public static void init() {
        library = new Library();
    }

    @AfterEach
    public void clearAll() {
        library = new Library();
    }

    @Test
    public void buyBookTestCatalog() {
        Book book = new Book("schildt", "Intro to Java", 2017, "345123isbn");
        library.buyBook(book, 5);
        assertEquals(1, library.getBooks().size());
        assertTrue(library.getBooks().contains(book));
        for (Book testBook :
                library.getBooks()) {
            assertEquals(testBook.getTitle(), book.getTitle());
            assertEquals(testBook.getIsbn(), book.getIsbn());
        }
    }

    @Test
    public void buyBookTestStore() {
        Book book = new Book("schildt", "Intro to Java", 2017, "345123isbn");
        library.buyBook(book, 5);
        assertEquals(5, library.getBookInstances().size());
        for (BookInstance bookInstance :
                library.getBookInstances()) {
            assertEquals(bookInstance.getBook().getTitle(), book.getTitle());
            assertEquals(bookInstance.getBook().getIsbn(), book.getIsbn());
        }
    }

    @Test
    public void takeBookReaderTest() {
        Book book1 = new Book("schildt", "Intro to Java", 2017, "345123isbn");
        Book book2 = new Book("Snowden", "How to hack pentagon", 2017, "345112isbn");

        Reader john = new Reader("John", "Connor", "Androvich", "email", 12345678);

        library.buyBook(book1, 5);

        assertEquals(0, library.getBookings().size());
        assertEquals(0, library.getReaders().size());

        library.takeBook(john, book1, 10);

        assertEquals(1, library.getBookings().size());
        assertEquals(4, library.getBookInstances().size());
        assertEquals(1, library.getReaders().size());

        assertTrue(library.getReaders().contains(john));

        for (Booking booking :
                library.getBookings()) {
            assertTrue(booking.getReader().equals(john));
            assertTrue(!booking.getBookInstance().getBook().equals(book2));
            assertTrue(!booking.getBookInstance().getBook().getTitle().equals(book2.getTitle()));
        }
    }

    @Test
    public void takeBookOtherReaderTest() {
        Book book1 = new Book("schildt", "Intro to Java", 2017, "345123isbn");

        Reader john = new Reader("John", "Connor", "Androvich", "email",  12345678);
        Reader sarah = new Reader("Sarah", "Connor", "Human","email", 12345679);

        library.buyBook(book1, 5);

        library.takeBook(john, book1, 10);

        assertTrue(!library.getReaders().contains(sarah));

        for (Booking booking :
                library.getBookings()) {
            assertTrue(!booking.getReader().equals(john));
            assertTrue(booking.getBookInstance().getBook().equals(book1));
            assertTrue(booking.getBookInstance().getBook().getTitle().equals(book1.getTitle()));
        }
    }

    @Test
    public void returnBookTest() {
        Book book1 = new Book("schildt", "Intro to Java", 2017, "345123isbn");

        Reader john = new Reader("John", "Connor", "Androvich", "email",  12345678);

        library.buyBook(book1, 5);

        library.takeBook(john, book1, 10);
        library.returnBook(john, book1);


        assertEquals(0, library.getBookings().size());
        assertEquals(1, library.getReaders().size());
        assertEquals(5, library.getBookInstances().size());
    }
}
package test;

import com.company.library.Library;
import com.company.library.models.Book;
import com.company.library.models.BookInstance;
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


}
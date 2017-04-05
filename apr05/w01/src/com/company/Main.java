package com.company;

import com.company.library.Library;
import com.company.library.Utils.DataManager;
import com.company.library.models.Book;
import com.company.library.models.Reader;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("schildt","Intro to Java",  2017, "345123isbn");
        Book book2 = new Book("Snowden","How to hack pentagon", 2017, "345112isbn");
        Book book3 = new Book("Author", "Secret Book", 2010, "345121isbn");

        // закупаем две книги
        library.buyBook(book1 , 5);
        library.buyBook(book2 , 5);

        Reader john = new Reader("John", "Connor", "Androvich", 12345678);
        Reader sarah = new Reader("Sarah", "Connor", "Human", 12345679);

        // берем кнги
        library.takeBook(john, book1);
        library.takeBook(sarah, book1);
        library.takeBook(sarah, book2);
        library.takeBook(sarah, book3);

        library.returnBook(sarah, book2);

        library.showAllData();

        DataManager.serializeToFile(library.getBooks());

        library = new Library();

        for (Book book: DataManager.deserialize()) {
            library.buyBook(book, 5);
        }

        library.showAllData();

    }
}

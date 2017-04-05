package com.company;

import com.company.library.Library;
import com.company.library.Utils.DataManager;
import com.company.library.models.Book;
import com.company.library.models.Reader;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

//        library.setCatalog(DataManager.deserialize());
//        DataManager.deserialize();

        for (Book book: DataManager.deserialize()) {
            library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
        }

        Reader john = new Reader("John", "Connor", "Androvich", 12345678);
        Reader reader = new Reader("Sara", "Connor", "Human", 12345679);

        library.buyBook("Intro to Java", "schildt", "123asd", 2017 , 5);
        library.buyBook("How to hack pentagon", "Snowden", "124asd", 2015 , 5);

        library.takeBook("John", "Connor", "Androvich", 12345678,"Intro to Java");
        library.takeBook("Sara", "Connor", "Human", 12345679,"How to hack pentagon");

        library.returnBook("John", "Connor", "Androvich", 12345678, "Intro to Java");

        library.showAllData();

        DataManager.serializeToFile(library.getCatalog());

    }
}

package com.company;

import com.company.library.Library;
import com.company.library.models.Book;
import com.company.library.models.Reader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static {
//        PropertyConfigurator.configure("/home/eku/proj/stc/apr05/w01/log4j.properties");
//        DOMConfigurator.configure("log4j.xml");
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        MyClassLoader myClassLoader = new MyClassLoader();

        try {
//            Class aClass = Class.forName("Ticket", true, myClassLoader);
//            System.out.println("aClass.getName() = " + aClass.getName());
//            Object ticket = aClass.newInstance();
//            Method method = aClass.getMethod("getName");
//            System.out.println(method.invoke(ticket));

            Class aClass = Class.forName("com.company.library.models.Book", true, myClassLoader);
            System.out.println("aClass.getName() = " + aClass.getName());
            Object ticket = aClass.newInstance();
            Field field = aClass.getField("reloadedVar");
            System.out.println(field.get(ticket));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//        Library library = new Library();
////
//        Book book1 = new Book("schildt", "Intro to Java", 2017, "345123isbn");
//        Book book2 = new Book("Snowden", "How to hack pentagon", 2017, "345112isbn");
//        Book book3 = new Book("Author", "Secret Book", 2010, "345121isbn");
//        library.buyBook(book1, 5);
//        library.buyBook(book2, 5);
//        library.buyBook(book3, 5);
//
//        Reader john = new Reader("John", "Connor", "Androvich", "john@server.ru", 12345678);
//        Reader sarah = new Reader("Sarah", "Connor", "Human", "sarah@server.ru", 12345679);
//        Reader terminator = new Reader("Terminator", "t1000", "lastName", "terminator@server.ru", 12345670);
//
//        // берем кнги
//        library.takeBook(john, book1, 10);
//        library.takeBook(sarah, book2, 20);
//        library.takeBook(sarah, book3, 50);
//
//
//        MailNotifer.checkBooking(library.getBookings());


//        System.out.println(book1.toXML());
//        Book book2 = new Book("Snowden", "How to hack pentagon", 2017, "345112isbn");
//        Book book3 = new Book("Author", "Secret Book", 2010, "345121isbn");
//
////        // закупаем две книги

//        library.buyBook(book2, 5);
////
//        Reader john = new Reader("John", "Connor", "Androvich", 12345678);
//        Reader sarah = new Reader("Sarah", "Connor", "Human", 12345679);
//
//        // берем кнги
//        library.takeBook(john, book1);
//        library.takeBook(sarah, book1);
//        library.takeBook(sarah, book2);
//        library.takeBook(sarah, book3);

//        library.returnBook(sarah, book2);
//
//        for (Booking booking:
//                library.getBookings()) {
//            System.out.println(booking.toXML());
//        }

//        library.showAllData();
//
//        library.serialize();
//
//        library = null;
//
//        library = Library.unserialize();
//
//        library.showAllData();


    }
}

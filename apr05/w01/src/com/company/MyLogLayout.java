package com.company;

import com.company.library.Library;
import com.company.library.models.Book;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class MyLogLayout extends PatternLayout {
    public String format(LoggingEvent event)
    {
        StringBuffer sb = new StringBuffer();
        System.out.println(event.getMessage().getClass());

        Library library = (Library)event.getMessage();
        for (Book book :
                library.getBooks()) {
            sb.append(book.toXML()).append("\n");
        }

        sb.append("\n");
        return sb.toString();

    }
}

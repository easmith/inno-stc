package com.company;

import com.company.library.models.Booking;
import com.company.library.models.Reader;

import java.util.Date;
import java.util.Set;

/**
 * Created by eku on 14.04.17.
 */
public class MailNotifer {
    public static void checkBooking(Set<Booking> bookings) {
        for (Booking booking :
                bookings) {
            if (new Date().after(booking.getReturnDate())) {
                sendMail(booking.getReader());
            }
        }
    }

    public static void sendMail (Reader reader) {
        System.out.println(reader.getEmail() + ": send email");
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;

import java.security.Principal;

/**
 * A interface that will hold hold abstract versions of all the methods we
 * would like to perform on Booking objects. This allows us to abstract
 * the implementation of the database side actions into a class such
 * that we can switch to a different database by just switching that class.
 */
public interface BookingRepository {

    public boolean addBooking(BookingForm bookingForm);

    public Object findAllUsersBookings(String username);
}

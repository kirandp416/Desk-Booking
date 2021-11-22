package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

/**
 * A interface that will hold hold abstract versions of all the methods we
 * would like to perform on Booking objects. This allows us to abstract
 * the implementation of the database side actions into a class such
 * that we can switch to a different database by just switching that class.
 */
public interface BookingRepository {

    public boolean addBooking(BookingDTO bookingDTO);

    public Object findAllUsersBookings(String username);

    public boolean deleteBooking(Integer id);
}
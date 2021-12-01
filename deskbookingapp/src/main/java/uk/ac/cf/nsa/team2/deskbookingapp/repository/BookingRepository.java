package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.AdminBookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;

import java.util.List;
import java.util.Optional;

/**
 * A interface that will hold hold abstract versions of all the methods we
 * would like to perform on Booking objects. This allows us to abstract
 * the implementation of the database side actions into a class such
 * that we can switch to a different database by just switching that class.
 */
public interface BookingRepository {

    public boolean addBooking(BookingForm bookingForm);

    public List<BookingDTO> findAllUsersBookings(String username);

    public boolean deleteBooking(Integer id);

    public Optional<List<AdminBookingDTO>> findAllBookingsAdmin();
}

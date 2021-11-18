package uk.ac.cf.nsa.team2.deskbookingapp.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.forms.BookingForm;

@Repository
public class BookingRepositoryJDBC implements BookingRepository {

    private JdbcTemplate jdbcTemplate;

    public BookingRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Implement addBooking method from BookingRepository Interface.
    // This method just adds a new booking to the booking table in
    // the MySQL database:

    @Override
    public boolean addBooking(BookingForm bookingForm) {

        // Some code that can be used to check that bookingForm
        // holds the correct data:

        // System.out.println("addBooking supplied the following date");
        // System.out.println(bookingForm.getBookingDate());

        // Use update function that returns the number of rows affected by
        // the update:

        int rows = jdbcTemplate.update("insert into booking (booking_date) values(?)",
                new Object[]{bookingForm.getBookingDate()}
        );

        // If the number of rows affected by the update is more than zero,
        // the update must have worked. If this is the case we return true
        // back to the addBooking method caller:

        return rows > 0;
    }


}

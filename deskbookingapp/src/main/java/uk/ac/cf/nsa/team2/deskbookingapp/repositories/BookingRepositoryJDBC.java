package uk.ac.cf.nsa.team2.deskbookingapp.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.forms.BookingForm;

@Repository
public class BookingRepositoryJDBC implements BookingRepository{

    private JdbcTemplate jdbcTemplate;

    public BookingRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addBooking(BookingForm bookingForm){

        // Use update function that returns the number of rows affected by
        // the update.
        System.out.println("addBooking supplied the following date");
        System.out.println(bookingForm.getBookingDate());

        int rows = jdbcTemplate.update("insert into booking (booking_date) values(?)",
                new Object[]{bookingForm.getBookingDate()}
        );

        // if the number of rows affected by the update is more than zero,
        // the update must have worked. If this is the case we return true
        // back to the addBooking method caller

        return rows > 0;
    }


}

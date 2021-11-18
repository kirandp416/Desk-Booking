package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingRepository;

import java.util.List;

/**
 * A MYSQL JDBC implementation of the BookingRepository interface
 * that we have created.
 */
@Repository
public class BookingRepositoryJDBC implements BookingRepository {

    private JdbcTemplate jdbcTemplate;

    public BookingRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Implement method from BookingRepository that deals with adding
     * a new booking to the booking table in MySQL database.
     * @param bookingDTO A DTO booking object we can use to hold the
     *                   data associated with one booking submission.
     * @return if the number of rows affected is greater than
     * zero, we return true. Otherwise, we return false.
     */
    @Override
    public boolean addBooking(BookingDTO bookingDTO) {

        // Some code that can be used to check that bookingForm
        // holds the correct data:

        // System.out.println("addBooking supplied the following date");
        // System.out.println(bookingForm.getBookingDate());

        int rows = jdbcTemplate.update("insert into booking (username, booking_date) values(?,?)",
                new Object[]{bookingDTO.getUsername(), bookingDTO.getDate()}
        );

        if (rows > 0)
            return true;
        else
            return false;

    }

//    @Override
//    public List<BookingDTO> findAllUsersBookings(String username){
//        return jdbcTemplate.query("insert query here",
//                new BookingMapper());
//
//    }


}

package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.BookingMapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
     *
     * @param bookingForm An object with fields that match the booking form
     *                   fields.
     * @return if the number of rows affected is greater than
     * zero, we return true. Otherwise, we return false.
     */
    @Override
    public boolean addBooking(BookingForm bookingForm) {

        // Some code that can be used to check that bookingForm
        // holds the correct data:

        // System.out.println("addBooking supplied the following date");
        // System.out.println(bookingForm.getBookingDate());

        String query = "insert into booking (username, booking_date, room_id, desk_id, book_timestamp) values(?,?,?,?,?)";

        int rows = jdbcTemplate.update(query,
                new Object[]{bookingForm.getUsername(), bookingForm.getBookingDate(), bookingForm.getBookingRoomId(), bookingForm.getBookingDeskId(),
                        OffsetDateTime.now(ZoneOffset.UTC)}
        );

        if (rows > 0)
            return true;
        else
            return false;

    }

    /**
     * Implement method from BookingRepository that deals with returning all
     * bookings that a user has made by querying the MySQL database using that
     * user's username.
     * @param username The username of the currently logged-in user
     * @return Bookings returned as a List of BookingDTO objects
     */
    @Override
    public List<BookingDTO> findAllUsersBookings(String username) {
        String queryString =
                "SELECT booking_id, username, booking_date, book_timestamp, room_name, desk_name, desk_type_name, notes\n" +
                        "FROM booking bookings\n" +
                        "LEFT OUTER JOIN room rooms\n" +
                        "ON bookings.room_id = rooms.room_id\n" +
                        "LEFT OUTER JOIN (SELECT desk_id, room_id, desk.desk_type_id, desk_name, notes, desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id) desks_with_type\n" +
                        "ON bookings.desk_id = desks_with_type.desk_id\n" +
                        "WHERE username = ?";

        return jdbcTemplate.query(queryString,
                new Object[]{username},
                new BookingMapper());

    }

    /**
     * Implement method from BookingRepository that deals with deleting
     * a single booking from the table of bookings in the MySQL database
     * @param id The id of the booking that a delete is being attempted on
     * @return A boolean that will be true if 1 or more rows were affected
     * by the update. In all other cases, it will be false.
     */
    @Override
    public boolean deleteBooking(Integer id) {

        String query = "DELETE from booking WHERE booking_id=?";
        int rowsAffected = jdbcTemplate.update(query, id);

        return rowsAffected > 0;
    }

    /**
     *
     * Implement method from BookingRepository that deals with returning all
     * bookings that are in the system to the admin user. This is achieved by
     * querying the MySQL database for all bookings. Note that the bookings come
     * back in reverse chronological order i.e. bookings that are for days furtherest
     * into the future will be at the top of the query.
     *
     * @return List of BookingDTO objects
     */
    @Override
    public List<BookingDTO> findAllReverseChronologicalOrder(){
        String queryString =
                "SELECT booking_id, username, booking_date, book_timestamp, room_name, desk_name, desk_type_name, notes " +
                "FROM booking bookings " +
                "LEFT OUTER JOIN room rooms " +
                "ON bookings.room_id = rooms.room_id " +
                "LEFT OUTER JOIN (SELECT desk_id, room_id, desk.desk_type_id, desk_name, notes, desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id) desks_with_type " +
                "ON bookings.desk_id = desks_with_type.desk_id " +
                "ORDER BY booking_date DESC ";

        return jdbcTemplate.query(queryString, new BookingMapper());
    }


}

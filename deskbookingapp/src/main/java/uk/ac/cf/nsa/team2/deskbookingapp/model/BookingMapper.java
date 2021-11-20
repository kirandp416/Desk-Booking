package uk.ac.cf.nsa.team2.deskbookingapp.model;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * When we query our MySQL database for bookings, each booking will come back
 * as a row in MySQL table. If the query is being done in Java via the query()
 * (a method that belongs to Objects of the JdbcTemplate class) then Java will
 * see the rows coming back as ResultSet objects. In our framework we would like
 * to each row to come back as a BookingDTO object. The purpose of this class
 * is to create Objects that can facilitate the mapping of ResultSet Objects to
 * BoookingDTO Objects.
 */
public class BookingMapper implements RowMapper <BookingDTO> {
    
    /**
     * Method that allows us to pass a new BookingMapper to the query() method
     * and have query be able to take the ResultSet Objects that comeback and
     * map them to BookingDTO Objects.
     * @param rs ResultSet object which is Java's equivalent of a row in a table
     *           in a MySQL database
     * @param rowNum An int used to keep track of the rows as they are being mapped
     * @return A BookingDTO object
     * @throws SQLException
     */
    @Override
    public BookingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookingDTO(rs.getInt("booking_id"), rs.getString("username"), rs.getString("booking_date"));
    }
}

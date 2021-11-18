package uk.ac.cf.nsa.team2.deskbookingapp.model;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookingDTO(rs.getInt("booking_id"), rs.getString("username"), rs.getString("booking_date"));
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.AdminBookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingAdminMapper implements RowMapper<AdminBookingDTO> {

    @Override
    public AdminBookingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AdminBookingDTO(
                rs.getInt("booking_id"),
                rs.getString("username"),
                rs.getString("booking_date"),
                rs.getString("room_name"),
                rs.getString("desk_name"),
                rs.getString("desk_type_name"),
                rs.getString("notes")
        );
    }

}

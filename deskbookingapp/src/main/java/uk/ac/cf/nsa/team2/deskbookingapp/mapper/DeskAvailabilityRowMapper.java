package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class used to override one method. Overriding this method allows us to
 * instantiate DeskAvailabilityDTO objects from the rows that come back
 * from our query that is performed in the JDBC implementation.
 */
public class DeskAvailabilityRowMapper implements RowMapper<DeskAvailabilityDTO> {


    @Override
    public DeskAvailabilityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeskAvailabilityDTO(
                rs.getInt("current_desk_id"),
                rs.getInt("room_id"),
                rs.getString("desk_name"),
                new DeskTypeDTO(
                        rs.getInt("desk_type_id"),
                        rs.getString("desk_type_name"),
                        rs.getString("desk_type_introduce")
                ),
                rs.getString("notes"),
                rs.getInt("available"),
                rs.getInt("does_user_have_booking_on_that_day"),
                rs.getInt("does_user_have_that_desk_booked_on_that_day"),
                rs.getInt("booking_id")
        );
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeskAvailabilityRowMapper implements RowMapper<DeskAvailabilityDTO> {

    // Create a method that can translate a row coming back from
    // the database that corresponds to a desk and then instantiate
    // a DeskAvailabilityDTO object from that row
    @Override
    public DeskAvailabilityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeskAvailabilityDTO(
                rs.getInt("current_desk_id"),
                rs.getInt("room_id"),
                rs.getString("desk_name"),
                new DeskTypeDTO(
                        rs.getInt("desk_type_id"),
                        rs.getString("desk_type_name"),
                        rs.getString("photo"),
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

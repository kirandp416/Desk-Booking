package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityAdminDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeskAvailabilityAdminRowMapper implements RowMapper<DeskAvailabilityAdminDTO> {

    @Override
    public DeskAvailabilityAdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeskAvailabilityAdminDTO(
                rs.getInt("desk_id"),
                rs.getInt("room_id"),
                rs.getString("desk_name"),
                new DeskTypeDTO(rs.getInt("desk_type_id"), rs.getString("desk_type_name")),
                rs.getString("notes"),
                rs.getInt("available"),
                rs.getString("booked_by"),
                rs.getInt("booking_id")
        );
    }
}

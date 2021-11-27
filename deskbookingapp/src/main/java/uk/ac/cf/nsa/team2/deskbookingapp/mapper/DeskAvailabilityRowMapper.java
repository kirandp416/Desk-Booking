package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeskAvailabilityRowMapper implements RowMapper<DeskAvailabilityDTO> {

    @Override
    public DeskAvailabilityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeskAvailabilityDTO(
                rs.getInt("desk_id"),
                rs.getInt("room_id"),
                rs.getString("desk_name"),
                new DeskTypeDTO(
                        rs.getInt("desk_type_id"),
                        rs.getString("desk_type_name")
                ),
                rs.getString("notes"),
                rs.getInt("available")
        );
    }
}
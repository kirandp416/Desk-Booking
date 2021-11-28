package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A row mapper to map a room data row to a {@link RoomDTO}.
 */
public class RoomRowMapper implements RowMapper<RoomDTO> {

    @Override
    public RoomDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RoomDTO(
                rs.getInt("room_id"),
                rs.getString("room_name")
        );
    }

}

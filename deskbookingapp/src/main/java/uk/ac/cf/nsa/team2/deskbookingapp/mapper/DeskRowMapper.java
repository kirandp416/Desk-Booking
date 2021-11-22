package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A row mapper to map a data row to a {@link DeskDTO}.
 */
public class DeskRowMapper implements RowMapper<DeskDTO> {

    @Override
    public DeskDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeskDTO(
                rs.getInt("desk_id"),
                rs.getInt("room_id"),
                rs.getString("desk_name")
        );
    }

}

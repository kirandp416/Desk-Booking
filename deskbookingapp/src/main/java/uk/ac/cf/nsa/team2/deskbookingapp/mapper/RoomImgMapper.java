package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomImgDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/12
 */

/**
 * Used to match the column fields in the database to the properties in the java bean
 */
public class RoomImgMapper implements RowMapper<RoomImgDTO> {

    @Override
    public RoomImgDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RoomImgDTO(
                rs.getInt("room_id"),
                rs.getString("room_name"),
                rs.getString("room_img_url")
        );
    }
}

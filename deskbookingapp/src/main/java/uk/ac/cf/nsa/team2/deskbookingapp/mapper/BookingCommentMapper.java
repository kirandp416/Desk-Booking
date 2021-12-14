package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingCommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/4
 */
/**
 * Used to match the column fields in the database to the properties in the java bean
 */
public class BookingCommentMapper implements RowMapper<BookingCommentDTO> {
    @Override
    public BookingCommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookingCommentDTO(
                rs.getInt("room_id"),
                rs.getInt("desk_id"),
                rs.getString("username"),
                rs.getString("desk_name"),
                rs.getString("room_name"),
                rs.getDate("booking_date")
        );
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;

/**
 * An implementation of the {@link RoomRepository} which uses
 * a MySQL database as the underlying store via JDBC.
 */
public class RoomMySqlJdbcRepository implements RoomRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public RoomMySqlJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean add(RoomDTO room) {
        final String sql = "INSERT INTO room(room_name) VALUES(?)";
        int rowsAffected = 0;

        try {
            rowsAffected = jdbc.update(sql, room.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return rowsAffected == 1;
    }

}

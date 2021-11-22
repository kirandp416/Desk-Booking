package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.RoomRowMapper;

import java.util.List;
import java.util.Optional;

/**
 * An implementation of the {@link RoomRepository} which uses
 * a MySQL database as the underlying store via JDBC.
 */
@Repository
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

    @Override
    public Optional<List<RoomDTO>> findAll() {
        final String sql = "SELECT room_id, room_name FROM room;";

        try {
            return Optional.of(jdbc.query(sql, new RoomRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        // Return an empty optional if the query failed.
        return Optional.empty();
    }

}
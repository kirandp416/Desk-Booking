package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.DeskAvailabilityRowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.DeskRowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An implementation of the {@link DeskRepository} which uses
 * a MySQL database as the underlying store via JDBC.
 */
@Repository
public class DeskMySqlJdbcRepository implements DeskRepository {

    private final JdbcTemplate jdbc;

    /**
     * Initialises a new instance of the repository.
     *
     * @param jdbc the JdbcTemplate object injected by Spring.
     */
    @Autowired
    public DeskMySqlJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public boolean add(DeskDTO desk) {
        final String sql = "INSERT INTO desk(desk_id,room_id,desk_name) VALUES(?,?,?);";
        int rowsAffected = 0;

        try {
            rowsAffected = jdbc.update(sql, desk.getId(), desk.getRoomId(), desk.getName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return rowsAffected == 1;
    }

    @Override
    public Optional<Boolean> checkDeskNameExistsForRoom(int roomId, String deskName) {
        final String sql = "SELECT * FROM desk WHERE room_id = ? AND desk_name = ?;";

        try {
            List<Map<String, Object>> resultSet = jdbc.queryForList(sql, roomId, deskName);
            return Optional.of(!resultSet.isEmpty());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        final String sql = "DELETE FROM desk WHERE desk_id = ?;";
        int rowsAffected = 0;

        try {
            rowsAffected = jdbc.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        // Rows affected will be 1 if the deletion was successful.
        return rowsAffected == 1;
    }

    @Override
    public Optional<Boolean> exists(int id) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM desk WHERE desk_id = ?);";

        try {
            // Execute query.
            Integer result = jdbc.queryForObject(sql, Integer.class, id);

            // If the desk exists, the result will be 1, otherwise 0.
            if (result == null || result == 0) {
                return Optional.of(false);
            }

            return Optional.of(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<DeskDTO>> findByRoom(int roomId, int offset, int limit) {
        final String sql = "SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?;";

        try {
            return Optional.of(jdbc.query(sql, new DeskRowMapper(), roomId, limit, offset));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> findByRoomCount(int roomId) {
        final String sql = "SELECT COUNT(*) FROM desk WHERE room_id = ?;";

        try {
            return Optional.ofNullable(jdbc.queryForObject(sql, Integer.class, roomId));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Implementation of the method in the DeskRepository interface. For a detailed explanation
     * of this method without regard to implementation, please see the comments for that method.
     * This implementation is done in SQL. We join the desk table and booking table for a given
     * date and room. We then add a column of integers to that join (1 for true, 0 for false)
     * that indicates whether the desk is available (1) or not available (0) in the given room
     * on the given date. The results of the query are then passed to a RowMapper constructor
     * such that we can return a list of DeskAvailabilityDTO objects from the results of the
     * query on the SQL database. The offset and limit parameters allow us to return chunks of
     * the total results from the SQL database, so that we can paginate the desks on the client
     * side.
     *
     * @param roomId The id of the room
     * @param date   The date of the availability that the function caller is interested in
     * @param offset The number of desks to offset by
     * @param limit  The maximum number of desks that should be returned
     * @return The chunk of desk objects we would like to render on the page given the
     * constraints of the pagination
     */
    @Override
    public Optional<List<DeskAvailabilityDTO>> findByRoomIncludeAvailability(int roomId, String date, int offset, int limit) {
        String queryString = "SELECT desks.desk_id, desks.room_id, desks.desk_name, CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available FROM (SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?) as desks LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id ORDER BY desks.desk_id";
        try {
            return Optional.of(jdbc.query(queryString, new DeskAvailabilityRowMapper(), roomId, limit, offset, date));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}

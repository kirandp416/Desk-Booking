package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityAdminDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.DeskAvailabilityAdminRowMapper;
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
        final String sql = "INSERT INTO desk(desk_id,room_id,desk_type_id,desk_name,notes) VALUES(?,?,?,?,?);";
        int rowsAffected = 0;

        try {
            rowsAffected = jdbc.update(sql, desk.getId(), desk.getRoomId(), desk.getDeskType().getDeskTypeId(),
                    desk.getName(), desk.getNotes());
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
        final String sql = "SELECT * FROM desk " +
                "INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id " +
                "WHERE room_id = ? " +
                "LIMIT ? OFFSET ?;";

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
    public Optional<List<DeskAvailabilityDTO>> findByRoomIncludeAvailability(String username, int roomId, String date, int offset, int limit) {

        // The queryString below will be passed the following parameters:

        // parameter 1 is String username
        // parameter 2 is String date
        // parameter 3 is String username
        // parameter 4 is String date
        // parameter 5 is String username
        // parameter 6 is String date
        // parameter 7 is int roomId
        // parameter 8 is int limit
        // parameter 9 is int offset
        // parameter 10 is String date

        String queryString = "SELECT desks.desk_id as current_desk_id, desks.room_id, desks.desk_type_id, desk_type.desk_type_name, desk_type.desk_type_introduce,desks.desk_name, desks.notes, CASE WHEN booking_date IS NOT NULL THEN FALSE ELSE TRUE END AS available, CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ?) > 0 THEN 1 ELSE 0 END AS does_user_have_booking_on_that_day, CASE WHEN (SELECT COUNT(*) FROM booking where username = ? and booking_date = ? and desk_id = current_desk_id) > 0 THEN 1 ELSE 0 END AS does_user_have_that_desk_booked_on_that_day, (SELECT booking_id FROM booking WHERE username = ? AND booking_date = ? AND desk_id=current_desk_id) as booking_id FROM (SELECT * FROM desk WHERE room_id = ? LIMIT ? OFFSET ?) as desks LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as bookings ON desks.desk_id = bookings.desk_id AND desks.room_id = bookings.room_id LEFT JOIN desk_type ON desks.desk_type_id = desk_type.desk_type_id ORDER BY desks.desk_id;";

        try {
            return Optional.of(jdbc.query(queryString, new DeskAvailabilityRowMapper(), username, date, username, date, username, date, roomId, limit, offset, date));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();


    }

    /**
     * Implementation of the method with the same name in the DeskRepository interface. This
     * implementation queries a MySQL database by joining multiple tables. The result of the
     * query is a table of desks in a given room and given date. Additionally, columns have
     * been added for whether the desks are available or not on that date. If they are not
     * available then the username of the person who booked it and the booking id of the booking
     * are included as columns too. If the booking username and booking id are not applicable
     * to a desk then these fields will have null values. Once the query results come back they
     * are mapped to a DTO object via DeskAvailabilityAdminRowMapper().
     * @param roomId ID of the room in question
     * @param date Date the admin is interested in
     * @param offset The first desk id to be shown in the current page in pagination
     * @param limit The total amount of desks to be shown per page in pagination.
     * @return Optional<List<DeskAvailabilityAdminDTO>>
     */
    @Override
    public Optional<List<DeskAvailabilityAdminDTO>> findByRoomIncludeAvailabilityForAdmin(int roomId, String date, int offset, int limit) {

        // The queryString below will be passed the following parameters:

        // parameter 1 is int roomId
        // parameter 2 is limit
        // parameter 3 is offset
        // parameter 4 is String date

        String queryString = "SELECT desk_with_type.desk_id, desk_with_type.room_id, desk_with_type.desk_name, desk_with_type.desk_type_id, desk_with_type.desk_type_name, desk_with_type.notes, CASE WHEN booking_id IS NULL THEN 1 ELSE 0 END AS available, booking.username as booked_by, booking.booking_id FROM (SELECT desk.desk_id, desk.room_id, desk.desk_name, desk.notes, desk_type.desk_type_id, desk_type.desk_type_name FROM desk INNER JOIN desk_type ON desk.desk_type_id = desk_type.desk_type_id WHERE desk.room_id = ? LIMIT ? OFFSET ?) AS desk_with_type LEFT JOIN (SELECT * FROM booking WHERE booking_date = ?) as booking ON desk_with_type.desk_id = booking.desk_id ORDER BY desk_id";

        try {
            return Optional.of(jdbc.query(queryString, new DeskAvailabilityAdminRowMapper(), roomId, limit, offset, date));
        }catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


}

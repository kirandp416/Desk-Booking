package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;

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

}

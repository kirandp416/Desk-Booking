package uk.ac.cf.nsa.team2.deskbookingapp.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.UserDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJDBC implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<UserDTO>> findAll() {
        String query = "SELECT username FROM user";

        try {
            return Optional.of(jdbcTemplate.query(query, new UserMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }


}

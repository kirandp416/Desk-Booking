package uk.ac.cf.nsa.team2.deskbookingapp.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.EmployeeDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.EmployeeMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryJDBC implements EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    /**
     * I did a thing and it was a blah
     * @param jdbcTemplate
     */
    public EmployeeRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<EmployeeDTO>> findAll() {
        String query = "SELECT username FROM employee";

        try {
            return Optional.of(jdbcTemplate.query(query, new EmployeeMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }


}

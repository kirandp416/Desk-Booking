package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.EmployeeDTO;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class used to override one method. Overriding this method allows us to
 * instantiate EmployeeDTO objects from the rows that come back
 * from our query that is performed in the JDBC implementation.
 */
public class EmployeeMapper implements RowMapper<EmployeeDTO> {

    @Override
    public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new EmployeeDTO(rs.getString("username"));
    }
}

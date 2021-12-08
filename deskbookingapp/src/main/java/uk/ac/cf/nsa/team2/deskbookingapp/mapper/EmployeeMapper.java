package uk.ac.cf.nsa.team2.deskbookingapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.EmployeeDTO;


import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<EmployeeDTO> {

    @Override
    public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new EmployeeDTO(rs.getString("username"));
    }
}

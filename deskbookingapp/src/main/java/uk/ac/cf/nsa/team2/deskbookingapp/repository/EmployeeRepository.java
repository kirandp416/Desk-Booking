package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    /**
     * A method to return all employees in the system.
     * @return Optional<List<EmployeeDTO>>
     */
    public Optional<List<EmployeeDTO>> findAll();


}


package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    public Optional<List<EmployeeDTO>> findAll();


}


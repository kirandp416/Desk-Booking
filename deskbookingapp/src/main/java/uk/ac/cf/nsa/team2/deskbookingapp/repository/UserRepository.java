package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public Optional<List<UserDTO>> findAll();


}


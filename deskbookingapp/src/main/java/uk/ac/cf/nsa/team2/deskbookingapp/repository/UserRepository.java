package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.apache.catalina.User;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.UserDTO;

public interface UserRepository {
    public User queryUserByName(String username );



    public UserDTO LoginIn(String name, String password);
}

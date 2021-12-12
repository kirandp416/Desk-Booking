package uk.ac.cf.nsa.team2.deskbookingapp.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.UserDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.UserRepository;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User queryUserByName(String username) {
        return userRepository.queryUserByName(username);
    }

    @Override
    public UserDTO LoginIn(String username, String password) {
        return userRepository.LoginIn(username, password);
    }
}

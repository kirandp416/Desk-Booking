package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.UserDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.UserRowMapper;

import java.util.List;

@Repository
public class UserMySqlJdbcRepository implements UserRepository {
    private final JdbcTemplate jdbc;

    /**
     * Initialises a new instance of the repository.
     *
     * @param jdbc the JdbcTemplate object injected by Spring.
     */
    @Autowired
    public UserMySqlJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public User queryUserByName(String username) {

        List<User> list = jdbc.query("SELECT * FROM users WHERE username = ? ", new Object[]{username}, new BeanPropertyRowMapper(UserDTO.class));
        if (list != null && list.size() > 0) {
            User user = list.get(0);
            return user;
        } else {
            return null;
        }

    }

    @Override
    public UserDTO LoginIn(String username, String password) {
        List<User> list = jdbc.query("SELECT * FROM users WHERE username = ? AND password", new Object[]{username, password}, new BeanPropertyRowMapper(UserDTO.class));
        if (list != null && list.size() > 0) {
            UserDTO userDTO = (UserDTO) list.get(0);
            return userDTO;
        } else {
            return null;
        }


    }
}
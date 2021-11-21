package uk.ac.cf.nsa.team2.deskbookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.pojo.RegisterUser;
import uk.ac.cf.nsa.team2.deskbookingapp.service.RegisterUserService;

import java.util.List;
import java.util.Map;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> findAll() {
        String sql="select * from registeruser";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public int save(RegisterUser user) {

        String sql="INSERT INTO registeruser(id,username,password) values ('?','?','?')";
        return jdbcTemplate.update(sql,user);
    }






}


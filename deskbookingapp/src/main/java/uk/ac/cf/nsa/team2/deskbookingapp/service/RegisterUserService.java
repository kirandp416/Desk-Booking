package uk.ac.cf.nsa.team2.deskbookingapp.service;

import uk.ac.cf.nsa.team2.deskbookingapp.pojo.RegisterUser;

import java.util.List;
import java.util.Map;

public interface RegisterUserService {

    //find all
    List<Map<String,Object>> findAll();
    //add data
    int save(RegisterUser user);


    //delete
   // Integer delete(int id);
    //select id find
    //User get(int id);
    //update data
    //int update(User user);

}

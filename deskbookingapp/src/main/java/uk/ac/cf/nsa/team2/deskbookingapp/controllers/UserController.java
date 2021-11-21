package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.web.bind.annotation.*;
import uk.ac.cf.nsa.team2.deskbookingapp.pojo.RegisterUser;
import uk.ac.cf.nsa.team2.deskbookingapp.service.RegisterUserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registeruser")
public class UserController {
    @Resource
    private RegisterUserService userService;


    @PostMapping("/add")
    public int add(@RequestBody RegisterUser user){
        return userService.save(user);
    }

    @GetMapping("/findAll")
    public List<Map<String,Object>> findAll() {
        return userService.findAll();
    }
}

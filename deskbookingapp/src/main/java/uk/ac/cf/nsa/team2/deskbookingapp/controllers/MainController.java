package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(path="/Home")
    public String home(){
        return "Home";
    }
    @RequestMapping(path = "/")
    public String navigation(){
        return "NavigationBar";
    }

    @RequestMapping(path = "/Booking")
    public  String booking(){
        return "Booking";
    }

    @RequestMapping(path="/UserLogin")
    public String UserLogin(){
        return "UserLogin" ;

    }






}

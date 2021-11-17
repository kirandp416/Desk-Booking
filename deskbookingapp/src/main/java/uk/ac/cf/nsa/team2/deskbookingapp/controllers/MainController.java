package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(path="/")
    public String home(){
        return "Home";
    }

    @RequestMapping(path="/book")
    public String book(){
        return "Book";
    }

}

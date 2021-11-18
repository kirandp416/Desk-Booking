package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // Route to get homepage
    @RequestMapping(path = "/")
    public String home() {
        return "Home";
    }

    // Route to get booking page
    @RequestMapping(path = "/book")
    public String book() {
        return "Book";
    }

}

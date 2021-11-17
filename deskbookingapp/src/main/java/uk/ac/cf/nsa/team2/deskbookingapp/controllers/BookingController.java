package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BookingController {

    @RequestMapping(path="/PostBooking", method = RequestMethod.POST)
    public String postBooking(){


        return "BookingAdded";
    }
}

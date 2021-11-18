package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    /**
     * Route for homepage
     * @return A string that will map to an
     * html file
     */
    @RequestMapping(path = "/")
    public String home() {
        return "Home";
    }

    /**
     * Route for Book a desk page
     * @return A string that will map to an
     *      * html file
     */
    @RequestMapping(path = "/book")
    public String book() {
        return "Book";
    }

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        return authentication.getName();
//    }

}

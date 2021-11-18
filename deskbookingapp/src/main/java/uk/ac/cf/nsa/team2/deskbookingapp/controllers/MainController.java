package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;

@Controller
public class MainController {

    /**
     * Route for homepage
     *
     * @return A string that will map to an
     * html file
     */
    @RequestMapping(path = "/")
    public String home() {
        return "Home";
    }

    /**
     * Route for Book a desk page
     *
     * @return A string that will map to an
     * * html file
     */
    @RequestMapping(path = "/book")
    public String book() {
        return "Book";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

}

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        return authentication.getName();
//    }



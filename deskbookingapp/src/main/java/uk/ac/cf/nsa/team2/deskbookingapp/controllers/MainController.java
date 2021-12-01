package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.MysqlCommonHandler;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.StringCommonHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

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

//    @RequestMapping(path = "/login")
//    public String login() {
//        return "Login";
//    }

//
//    /**
//     * Route for login page if already logged in.
//     *or redirect to login page if authentication fails
//     */
//
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

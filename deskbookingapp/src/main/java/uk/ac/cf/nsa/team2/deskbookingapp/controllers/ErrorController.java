package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * A controller for routes for displaying error pages.
 */
@Controller
public class ErrorController {

    /**
     * Route to get the internal server error page.
     *
     * @param response the HTTP response.
     * @return a ModelAndView object.
     */
    @GetMapping("/internal_server_error")
    public ModelAndView internalServerErrorPage(HttpServletResponse response) {
        // Set the HTTP response code to 500 internal.
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        return new ModelAndView("error/500");
    }

}

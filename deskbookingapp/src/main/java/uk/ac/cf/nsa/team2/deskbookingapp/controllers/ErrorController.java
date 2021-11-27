package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A controller for routes for displaying error pages.
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    /**
     * Route to handle errors.
     * Based on the error code, a custom error page is displayed.
     *
     * @param request The HTTP request.
     * @return A ModelAndView object.
     */
    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request) {
        // Adapted from https://www.baeldung.com/spring-boot-custom-error-page#1-a-custom-errorcontroller

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Return 500 error page if there was no specific error.
        if (status == null) {
            return new ModelAndView("/error/500");
        }

        // Parse status and return error page based on status code.
        HttpStatus code = HttpStatus.valueOf(Integer.parseInt(status.toString()));

        switch (code) {
            case FORBIDDEN:
                return new ModelAndView("/error/403");
            case NOT_FOUND:
                return new ModelAndView("/error/404");
            case INTERNAL_SERVER_ERROR:
                return new ModelAndView("/error/500");
            default:
                return new ModelAndView("/error/500");
        }
    }

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

package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import java.security.Principal;

@Controller
public class BookingController {

    private BookingRepository bookingRepository;

    /**
     * Constructor that will be used by Sprint to instantiate
     * a BookingRepository object automatically
     *
     * @param bRepo A BookingRepository object that we can use
     *              to hold our Booking objects in
     */
    @Autowired
    public BookingController(BookingRepository bRepo) {
        bookingRepository = bRepo;
    }

    /**
     * Route for Book a desk page
     *
     * @return A string that will map to an
     * * html file
     */
    @RequestMapping(path = "/booking/add")
    public String book() {
        return "Book";
    }

    /**
     * Post a new booking to the booking table in the database.
     * If it was successful we direct the user to BookingAdded
     * page. Otherwise, direct them to BookingNotAdded page.
     *
     * @param bookingForm A form object we can use to hold the html
     *                    form data for one booking submission.
     * @param br          A BindingResult object we can use to access
     *                    validation errors in our form object
     * @return A ModelAndView object
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/booking/add/process_form", method = RequestMethod.POST)
    public ModelAndView postBooking(BookingForm bookingForm, BindingResult br, Principal principal) {

        BookingDTO bookingDTO = new BookingDTO(bookingForm.getBookingDate(), principal.getName());
        ModelAndView mav = new ModelAndView();

        if (br.hasErrors()) {
            System.out.println("Binding Result Errors encountered.");
            mav.setViewName("BookingNotAdded");
            return mav;
        } else {
            if (bookingRepository.addBooking(bookingDTO)) {
                System.out.println("You added a booking.");
                mav.setViewName("BookingAdded");
                return mav;
            } else {
                System.out.println("No binding errors encountered but addBooking() method did not return true.");
                mav.setViewName("BookingNotAdded");
                return mav;
            }

        }

    }

    /**
     * Create a Model and View object that contains all of the bookings
     * that have been made by a particular user in the Model and the
     * Bookings page as the View
     *
     * @return a Model and View object
     */
    @RequestMapping(path = "/booking/all", method = RequestMethod.GET)
    public ModelAndView getUserBookingsPage(Principal principal) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("bookings", bookingRepository.findAllUsersBookings(principal.getName()));
        mav.setViewName("Bookings");
        return mav;
    }

    /**
     * Create route that will attempt to delete a booking from the booking
     * database, by booking id. If it is successful you will see a view that
     * say successful and if it is not you will see a view that says it failed.
     * We will be calling this method via AJAX so you will not see these views.
     * However, if you would like to see the views and test it, please change
     * request method below to GET and try the route with a valid id in the
     * address bar of browser.
     *
     * @param id the Booking id
     * @return ModelAndView object with a view that will tell you if deletion
     * was a success.
     */
    @RequestMapping(path = "/booking/delete", method = RequestMethod.DELETE)
    public ModelAndView bookingDelete(@RequestParam(value = "id", defaultValue = "null") String id) {

        ModelAndView mav = new ModelAndView();

        if (!id.equals("null")) {
            Integer idInt = Integer.valueOf(id);
            if (bookingRepository.deleteBooking(idInt)) {
                mav.setViewName("BookingDeleteSuccess");
            } else {
                mav.setViewName("BookingDeleteFail");
            }
        } else {
            mav.setViewName("BookingDeleteFail");
        }

        return mav;

        }


}

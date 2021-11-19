package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingRepository;

import java.security.Principal;

@Controller
public class BookingController {

    private BookingRepository bookingRepository;

    /**
     * Constructor that will be used by Sprint to instantiate
     * a BookingRepository object automatically
     * @param bRepo A BookingRepository object that we can use
     *              to hold our Booking objects in
     */
    @Autowired
    public BookingController(BookingRepository bRepo) {
        bookingRepository = bRepo;
    }

    /**
     * Route for Book a desk page
     * @return A string that will map to an
     *      * html file
     */
    @RequestMapping(path = "/booking/add")
    public String book() {
        return "Book";
    }

    /**
     * Post a new booking to the booking table in the database.
     * If it was successful we direct the user to BookingAdded
     * page. Otherwise, direct them to BookingNotAdded page.
     * @param bookingForm A form object we can use to hold the html
     *                    form data for one booking submission.
     * @param br A BindingResult object we can use to access
     *           validation errors in our form object
     * @return A ModelAndView object
     */
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
     * @return a Model and View object
     *
     */
    @RequestMapping(path="/booking/all", method = RequestMethod.GET)
    public ModelAndView getUserBookingsPage(Principal principal){

        ModelAndView mav = new ModelAndView();
        mav.addObject("bookings", bookingRepository.findAllUsersBookings(principal.getName()));
        mav.setViewName("Bookings");
        return mav;
    }

    @ResponseBody
    @RequestMapping(path="/booking/delete", method= RequestMethod.DELETE)
    public String bookingDelete(@RequestParam(value="id", defaultValue = "null") String StringId){

        Integer id;

        if (!StringId.equals("null")){
            id  = Integer.valueOf(StringId);
        }
        else{
            return "null";
        }

        bookingRepository.deleteBooking(id);

        return "deletion complete";
    }

}

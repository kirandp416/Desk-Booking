package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.AdminBookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    private BookingRepository bookingRepository;

    private RoomRepository roomRepository;

    /**
     * Constructor that will be used by Sprint to instantiate
     * a BookingRepository object automatically
     *
     * @param bRepo A BookingRepository object that we can use
     *              to hold our Booking objects in
     */
    @Autowired
    public BookingController(BookingRepository bRepo, RoomRepository rRepo) {
        bookingRepository = bRepo;
        roomRepository = rRepo;
    }

    /**
     * Create route to booking page. As page loads we load in the
     * currently logged in user's details so that they can make
     * a booking for their own personal account
     * @param principal An object containing the users details
     *
     * @return ModelAndView object which is the booking page
     *                      with user's details in the Model.
     */
    @RequestMapping(path = "/booking/add")
    public ModelAndView book(Principal principal) {

        Optional<List<RoomDTO>> rooms = roomRepository.findAll();

        if (rooms.isEmpty()) {
            return new ModelAndView("redirect:/internal_server_error");
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("rooms", rooms.get());
        mav.addObject("user", principal);
        mav.setViewName("/book/Book");

        return mav;
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
    public ModelAndView postBooking(BookingForm bookingForm, BindingResult br) {
        System.out.println(bookingForm.getUsername());
        System.out.println(bookingForm.getBookingDate());
        System.out.println(bookingForm.getBookingDeskId());
        System.out.println(bookingForm.getBookingRoomId());

        ModelAndView mav = new ModelAndView();

        if (br.hasErrors()) {
            System.out.println("Binding Result Errors encountered.");
            System.out.println(br.getAllErrors());
            mav.setViewName("/book/BookingNotAdded");
            return mav;
        } else {
            if (bookingRepository.addBooking(bookingForm)) {
                System.out.println("You added a booking.");
                mav.setViewName("/book/BookingAdded");
                return mav;
            } else {
                System.out.println("No binding errors encountered but addBooking() method did not return true.");
                mav.setViewName("/book/BookingNotAdded");
                return mav;
            }

        }

    }

    /**
     * Create a Model and View object that contains all of the bookings
     * that have been made by a particular user in the Model and the
     * Bookings page as the View
     *
     * @return a Model and View object that contains all the bookings
     */
    @RequestMapping(path = "/booking/all", method = RequestMethod.GET)
    public ModelAndView getUserBookingsPage(Principal principal) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("bookings", bookingRepository.findAllUsersBookings(principal.getName()));
        mav.setViewName("/book/Bookings");
        return mav;
    }

    /**
     * Create route that will attempt to delete a booking from the booking
     * database, by booking id. If it is successful you will see a view that
     * says successful and if it is not you will see a view that says it failed.
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
                mav.setViewName("/book/BookingDeleteSuccess");
            } else {
                mav.setViewName("/book/BookingDeleteFail");
            }
        } else {
            mav.setViewName("/book/BookingDeleteFail");
        }

        return mav;

        }

    /**
     * Create a route that will fetch list of all bookings
     * made from the database and show it in the manage_bookings page.
     * @return ModelandView that will display list of all bookings.
     */

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin/manage/booking")
    public ModelAndView manageBooking(){

        Optional<List<AdminBookingDTO>> bookings = bookingRepository.findAllBookingsAdmin();
        // If the optional is empty, redirect user to server error page.

        if (bookings.isEmpty()) {
            return new ModelAndView("redirect:/internal_server_error");
        }

        // Return a model and view for the manage desks page,
        // passing the rooms into the view.

        return new ModelAndView("admin/manage_bookings")
                .addObject("bookings", bookings.get());
        }

    /**
     * This method was adapted from HOs method called bookingDelete in file BookingController.java,
     * that was used to delete booking from database.
     *
     *This method will take the id of the booking which is passed through nav
     * and will pass it to the existing method to delete the booking
     * @param id the booking id
     * @return ModelAndView object with a view that will tell you if deletion
     * was a success.
     */
    @RequestMapping(path = "/admin/booking/delete", method = RequestMethod.DELETE)
    public ModelAndView adminBookingDelete(@RequestParam(value = "id", defaultValue = "null") String id) {

        ModelAndView mav = new ModelAndView();

        if (!id.equals("null")) {
            Integer idInt = Integer.valueOf(id);
            if (bookingRepository.deleteBooking(idInt)) {
                mav.setViewName("/admin/manage_bookings");
            } else {
                mav.setViewName("redirect:/internal_server_error");
            }
        } else {
            mav.setViewName("redirect:/internal_server_error");
        }

        return mav;

    }

}

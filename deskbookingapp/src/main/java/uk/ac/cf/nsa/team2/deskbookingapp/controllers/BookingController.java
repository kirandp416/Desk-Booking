package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.forms.BookingForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repositories.BookingRepository;

@Controller
public class BookingController {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bRepo){
        bookingRepository = bRepo;
    }


    @RequestMapping(path="/PostBooking", method = RequestMethod.POST)
    public ModelAndView postBooking(BookingForm bookingForm, BindingResult br){

        ModelAndView mav = new ModelAndView();

        if (br.hasErrors()){
            System.out.println("Binding Result Errors encountered.");
            mav.setViewName("BookingNotAdded");
            return mav;
        }else{
            if (bookingRepository.addBooking(bookingForm)){
                System.out.println("You added a booking.");
                mav.setViewName("BookingAdded");
                return mav;
            }
            else{
                System.out.println("No binding errors encountered but addBooking() method did not return true.");
                mav.setViewName("BookingNotAdded");
                return mav;
            }

        }

    }
}

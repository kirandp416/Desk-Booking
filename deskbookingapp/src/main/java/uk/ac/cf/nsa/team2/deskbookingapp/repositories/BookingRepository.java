package uk.ac.cf.nsa.team2.deskbookingapp.repositories;

import uk.ac.cf.nsa.team2.deskbookingapp.forms.BookingForm;

// Create an interface BookingRepository that will hold abstract
// versions of all the methods we would like to perform on Booking
// objects. Creation of an interface like this allows us to abstract
// the implementation of the database side actions into a class such
// that we can switch to a different database by just switching that class.

public interface BookingRepository {

    public boolean addBooking(BookingForm bookingForm);
}

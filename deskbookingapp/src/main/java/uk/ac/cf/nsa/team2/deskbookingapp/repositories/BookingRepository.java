package uk.ac.cf.nsa.team2.deskbookingapp.repositories;

import uk.ac.cf.nsa.team2.deskbookingapp.forms.BookingForm;

public interface BookingRepository {

    public boolean addBooking(BookingForm bookingForm);
}

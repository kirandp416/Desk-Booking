package uk.ac.cf.nsa.team2.deskbookingapp.forms;

/**
 * A class that is instantiated when someone submits a booking.
 * One BookingForm object will hold all the data that was submitted
 * for one particular booking.
 */
public class BookingForm {

    private String bookingDate;

    public BookingForm(String bookingDate) {
        this.bookingDate = bookingDate;

        // Some code to check what data is coming in to the constructor
        // from the form for server-side debugging

//        System.out.println("BookingForm object instantiated with bookingDate:");
//        System.out.println(this.bookingDate.length());
//        System.out.println("The BookingForm constructor was supplied:");
//        System.out.println(bookingDate);
    }

    public String getBookingDate() {
        return bookingDate;
    }
}

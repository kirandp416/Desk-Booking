package uk.ac.cf.nsa.team2.deskbookingapp.forms;

public class BookingForm {

    private String bookingDate;

    public BookingForm(String bookingDate) {
        this.bookingDate = bookingDate;

        // Some code to check what data is coming in to the contructor
        // from the form

         System.out.println("BookingForm object instantiated with bookingDate:");
         System.out.println(this.bookingDate.length());
         System.out.println("The BookingForm constructor was supplied:");
         System.out.println(bookingDate);
    }

    public String getBookingDate() {
        return bookingDate;
    }
}

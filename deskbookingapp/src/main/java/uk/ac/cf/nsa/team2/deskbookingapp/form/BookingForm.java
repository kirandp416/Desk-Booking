package uk.ac.cf.nsa.team2.deskbookingapp.form;

/**
 * A class that is instantiated when someone submits a booking.
 * One BookingForm object will hold all the data that was submitted
 * for one particular booking.
 */
public class BookingForm {

    private int bookingDeskId;
    private int bookingRoomId;
    private String bookingDate;
    private String username;

//    public BookingForm(String bookingDate) {
//        this.bookingDate = bookingDate;
//
//        // Some code to check what data is coming in to the constructor
//        // from the form for server-side debugging
//
////        System.out.println("BookingForm object instantiated with bookingDate:");
////        System.out.println(this.bookingDate.length());
////        System.out.println("The BookingForm constructor was supplied:");
////        System.out.println(bookingDate);
//    }


    public BookingForm(int bookingDeskId, int bookingRoomId, String bookingDate, String username) {
        this.bookingDeskId = bookingDeskId;
        this.bookingRoomId = bookingRoomId;
        this.bookingDate = bookingDate;
        this.username = username;
    }

    public int getBookingDeskId() {
        return bookingDeskId;
    }

    public int getBookingRoomId() {
        return bookingRoomId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getUsername() {
        return username;
    }
}

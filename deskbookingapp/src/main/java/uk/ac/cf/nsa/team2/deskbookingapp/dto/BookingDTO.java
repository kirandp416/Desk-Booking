package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * A class that will be used to instantiate Objects
 * that each represent one booking made in our system.
 */
public class BookingDTO {

    private int id;
    private String username;
    private String date;
    private String dateOrderedForDisplay;
    private String roomName;
    private String deskName;
    private String deskType;
    private String deskNotes;
    private OffsetDateTime timestamp; // UTC offset timestamp of booking.

    public BookingDTO(int id, String username, String date, String roomName, String deskName, String deskType, String deskNotes,
                      OffsetDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.roomName = roomName;
        this.deskName = deskName;
        this.deskType = deskType;
        this.deskNotes = deskNotes;
        this.timestamp = timestamp;
        this.dateOrderedForDisplay = dateReOrderer(date);
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDeskName() {
        return deskName;
    }

    public String getDeskType() {
        return deskType;
    }

    public String getDeskNotes() {
        return deskNotes;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getDateOrderedForDisplay() {
        return dateOrderedForDisplay;
    }

    public String getUsername() {
        return username;
    }

    // Create a method that takes the date of the booking on the database (which
    // is in the form yyyy-mm-dd) and reformats it to a more user-friendly format.
    // At the time of writing this comment, the method converts the date to the
    // form dd-mm-yy. However, developers can change this to other formats by giving
    // the constructor for the object named dateFormatRequired, other arguments. For
    // example, you could switch it to American dates by passing it "MM-dd-YY".

    public String dateReOrderer(String dateString) {

        SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = dateFormatDB.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Parse Exception thrown. Variable date currently points to: " + date);
            e.printStackTrace();
            return "null";
        }

        SimpleDateFormat dateFormatRequired = new SimpleDateFormat("dd-MM-yy");

        return dateFormatRequired.format(date);

    }
}

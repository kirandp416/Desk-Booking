package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Adapted class from HO's class called BookingDTO in file dto
 * that was used to instantiate Objects
 * that each represent booking made in our system.
 *
 * I have updated the class to display all the booking data without any id input.
 * and send the details requested to controller.
 * */
public class AdminBookingDTO {
    private int id;
    private String username;
    private String date;
    private String dateOrderedForDisplay;
    private String roomName;
    private String deskName;
    private String deskType;
    private String deskNotes;


    public AdminBookingDTO(int id,String username, String date, String roomName, String deskName, String deskType, String deskNotes) {
        this.id=id;
        this.username = username;
        this.date = date;
        this.roomName = roomName;
        this.deskName = deskName;
        this.deskType = deskType;
        this.deskNotes = deskNotes;
        this.dateOrderedForDisplay = dateReOrderer(date);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public String getDateOrderedForDisplay() {
        return dateOrderedForDisplay;
    }

    public String dateReOrderer(String dateString){

        SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = dateFormatDB.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormatRequired = new SimpleDateFormat("dd-MM-yy");

        return dateFormatRequired.format(date);

    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * A class that will be used to instantiate Objects
 * that each represent one booking made in our system.
 */
public class BookingDTO {

    private int id;
    private String username;
    private String date;


    public BookingDTO(String date, String username) {
        this.date = date;
        this.username = username;
    }

    public BookingDTO(int id, String username, String date) {
        this.id = id;
        this.username = username;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }
}
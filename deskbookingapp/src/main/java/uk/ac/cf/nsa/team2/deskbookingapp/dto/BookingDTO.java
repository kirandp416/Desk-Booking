package uk.ac.cf.nsa.team2.deskbookingapp.dto;

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
}

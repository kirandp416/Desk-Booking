package uk.ac.cf.nsa.team2.deskbookingapp.dto;

public class BookingDTO {

    private int id;
    private String date;
    private String username;

    public BookingDTO(String date, String username) {
        this.date = date;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }
}

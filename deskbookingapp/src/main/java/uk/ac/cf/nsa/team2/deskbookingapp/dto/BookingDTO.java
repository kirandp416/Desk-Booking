package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * A class that will be used to instantiate Objects
 * that each represent one booking made in our system.
 */
public class BookingDTO {

    private int id;
    private String date;
    private String roomName;
    private String deskName;
    private String deskType;
    private String deskNotes;

    public BookingDTO(int id, String date, String roomName, String deskName, String deskType, String deskNotes) {
        this.id = id;
        this.date = date;
        this.roomName = roomName;
        this.deskName = deskName;
        this.deskType = deskType;
        this.deskNotes = deskNotes;
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
}

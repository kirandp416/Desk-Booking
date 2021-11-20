package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * A data transfer object for transferring desk data.
 */
public class DeskDTO {

    private int id; // The ID of the desk.
    private int roomId; // The ID of the room.
    private String name; // The name of the desk.

    public DeskDTO() {

    }

    public DeskDTO(int id, int roomId, String name) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

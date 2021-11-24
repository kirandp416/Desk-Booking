package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data transfer object for transferring desk data.
 */
public class DeskDTO {

    @JsonProperty("id")
    protected int id; // The ID of the desk.

    @JsonProperty("room_id")
    protected int roomId; // The ID of the room.

    @JsonProperty("name")
    protected String name; // The name of the desk.

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

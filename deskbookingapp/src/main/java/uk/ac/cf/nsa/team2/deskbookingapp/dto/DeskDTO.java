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

    @JsonProperty("desk_type")
    private DeskTypeDTO deskType; // Desk type info.

    @JsonProperty("name")
    protected String name; // The name of the desk.

    @JsonProperty("notes")
    private String notes; // Notes for the desk.

    public DeskDTO() {

    }

    public DeskDTO(int id, int roomId, DeskTypeDTO deskType, String name, String notes) {
        this.id = id;
        this.roomId = roomId;
        this.deskType = deskType;
        this.name = name;
        this.notes = notes;
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

    public DeskTypeDTO getDeskType() {
        return deskType;
    }

    public void setDeskType(DeskTypeDTO deskType) {
        this.deskType = deskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

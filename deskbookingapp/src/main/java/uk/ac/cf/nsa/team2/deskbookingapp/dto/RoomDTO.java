package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * Room data transfer object.
 */
public class RoomDTO {

    private int id;
    private String name;

    public RoomDTO() {

    }

    public RoomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

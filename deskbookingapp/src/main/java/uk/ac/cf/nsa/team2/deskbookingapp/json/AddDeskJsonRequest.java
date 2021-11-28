package uk.ac.cf.nsa.team2.deskbookingapp.json;

/**
 * JSON request model for adding a desk.
 */
public class AddDeskJsonRequest {

    private final int room;
    private final int deskType;
    private final String name;
    private final String notes;

    /**
     * Initialises a new instance of the JSON request model.
     *
     * @param room     the ID of the room to add the desk to.
     * @param deskType the ID of the desk type.
     * @param name     the name of the desk.
     * @param notes    the desk notes.
     */
    public AddDeskJsonRequest(int room, int deskType, String name, String notes) {
        this.room = room;
        this.deskType = deskType;
        this.name = name;
        this.notes = notes;
    }

    public int getRoom() {
        return room;
    }

    public int getDeskType() {
        return deskType;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

}

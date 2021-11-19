package uk.ac.cf.nsa.team2.deskbookingapp.json;

/**
 * JSON request model for adding a desk.
 */
public class AddDeskJsonRequest {

    private final int room;
    private final String name;

    /**
     * Initialises a new instance of the JSON request model.
     *
     * @param room the ID of the room to add the desk to.
     * @param name the name of the desk.
     */
    public AddDeskJsonRequest(int room, String name) {
        this.room = room;
        this.name = name;
    }

    public int getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

}

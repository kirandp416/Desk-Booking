package uk.ac.cf.nsa.team2.deskbookingapp.form;

public class RoomEditForm {

    private final int id;
    private final String name;

    public RoomEditForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

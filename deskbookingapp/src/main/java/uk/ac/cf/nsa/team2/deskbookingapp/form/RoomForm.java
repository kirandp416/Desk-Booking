package uk.ac.cf.nsa.team2.deskbookingapp.form;

public class RoomForm {

    private final String name;
    private final Integer id;
    public RoomForm(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}

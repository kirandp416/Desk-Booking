package uk.ac.cf.nsa.team2.deskbookingapp.form;

public class RoomCreateForm {

    private final String name;
    private final String url;

    public RoomCreateForm(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

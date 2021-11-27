package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data transfer object for transferring desk type data.
 */
public class DeskTypeDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public DeskTypeDTO() {

    }

    public DeskTypeDTO(int id, String name) {
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

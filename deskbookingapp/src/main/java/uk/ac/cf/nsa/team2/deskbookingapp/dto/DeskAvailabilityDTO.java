package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeskAvailabilityDTO extends DeskDTO {

    @JsonProperty("available")
    private boolean available; // a boolean that will be true if the desk is available


    public DeskAvailabilityDTO(int id, int roomId, String name, int available) {

        super(id, roomId, name);

        if (available == 1) {
            this.available = true;
        } else {
            this.available = false;
        }

    }
}

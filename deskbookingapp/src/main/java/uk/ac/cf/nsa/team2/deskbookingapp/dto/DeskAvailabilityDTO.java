package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Create a class the same as DeskDTO except that it holds a boolean
 * that says whether the desk is available or not on the date selected on
 * the client side. This boolean will be provided by creating a new column
 * in a table by joining and querying the desk and booking tables.
 */
public class DeskAvailabilityDTO extends DeskDTO {

    @JsonProperty("available")
    private boolean available; // a boolean that will be true if the desk is available

    public DeskAvailabilityDTO(int id, int roomId, String name, DeskTypeDTO deskType, String notes, int available) {

        // Leverage constructor in parent class

        super(id, roomId, deskType, name, notes);

        // Since the SQL cannot store booleans, we will use 1 for true and 0
        // for false in the db and then use those to numbers to create real
        // booleans on the server side.

        if (available == 1) {
            this.available = true;
        } else {
            this.available = false;
        }

    }
}

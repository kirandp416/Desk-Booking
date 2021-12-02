package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Create a class the same as DeskDTO except that it holds two useful booleans.
 * The first is called available that says whether the desk is available or not
 * on the date selected on the client side. This boolean will be provided by
 * creating a new column in a table by joining and querying the desk and booking tables.
 * The second boolean is called doesUserHaveBookingOnThatDay. This is created
 * in the same inner join mentioned and will be true if the logged-in user has
 * any bookings (in any desk, in any room) for the selected date.
 */
public class DeskAvailabilityDTO extends DeskDTO {

    @JsonProperty("available")
    private boolean available; // a boolean that will be true if the desk is available

    @JsonProperty("does_user_have_booking_on_that_day")
    private boolean doesUserHaveBookingOnThatDay; // a boolean that will be true if the user has any bookings on the date selected on client

    public DeskAvailabilityDTO(int id, int roomId, String name, DeskTypeDTO deskType, String notes, int available, int doesUserHaveBookingOnThatDay) {

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

        if (doesUserHaveBookingOnThatDay == 1){
            this.doesUserHaveBookingOnThatDay = true;
        }
        else
            this.doesUserHaveBookingOnThatDay = false;

    }
}

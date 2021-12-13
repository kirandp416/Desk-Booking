package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that is used to create data transfer objects for desks
 * for the admin user. These objects contain all of the information
 * that is stored about a desk in the desk table in the database. They
 * also carry some fields on whether they are booked on the date in question
 * and if they are booked then the username of the person who booked it and
 * the booking id of the booking.
 */
public class DeskAvailabilityAdminDTO extends DeskDTO{

    @JsonProperty("available")
    private boolean available; // Whether the desk has been booked or not

    @JsonProperty("booked_by")
    private String bookedBy; // The username of the person who booked it

    @JsonProperty("booking_id")
    private int bookingId; // The booking id of the booking

    public DeskAvailabilityAdminDTO(int deskId, int roomId, String deskName, DeskTypeDTO deskType,  String notes, int available, String bookedBy, int bookingId){

        super(deskId, roomId, deskType, deskName, notes);

        // int available, String bookedBy, int bookingId

        if (available == 1)
            this.available = true;
        else
            this.available = false;

        this.bookedBy = bookedBy;
        this.bookingId = bookingId;

    };




}

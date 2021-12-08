package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeskAvailabilityAdminDTO extends DeskDTO{

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("booked_by")
    private String bookedBy;

    @JsonProperty("booking_id")
    private int bookingId;

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

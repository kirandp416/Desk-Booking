package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data transfer object for transferring booking quota data.
 */
public class BookingQuotaDTO {

    @JsonProperty("quota")
    private int quota; // Total quota.

    @JsonProperty("used")
    private int used; // Used quota.

    @JsonProperty("remaining")
    private int remaining;// Remaining quota.

    public BookingQuotaDTO() {

    }

    public BookingQuotaDTO(int quota, int used, int remaining) {
        this.quota = quota;
        this.remaining = remaining;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

}

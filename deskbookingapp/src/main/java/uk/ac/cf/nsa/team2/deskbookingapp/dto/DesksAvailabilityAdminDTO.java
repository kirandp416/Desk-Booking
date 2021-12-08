package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A class adapted from DesksDTO class written by Hassan.
 */
public class DesksAvailabilityAdminDTO {

    @JsonProperty("results")
    private List<DeskAvailabilityAdminDTO> results;

    @JsonProperty("total_results")
    private int totalResults;

    public DesksAvailabilityAdminDTO(List<DeskAvailabilityAdminDTO> results, int totalResults) {
        this.results = results;
        this.totalResults = totalResults;
    }

    public List<DeskAvailabilityAdminDTO> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }
}

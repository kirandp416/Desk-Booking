package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A class adapted from DesksDTO class written by Hassan. The class allows
 * for retrieval of portions of the total desk cohort in JSON to allow for
 * pagination. The total_results field keeps track of the pagination.
 */
public class DesksAvailabilityDTO {

    @JsonProperty("results")
    private List<DeskAvailabilityDTO> results;

    @JsonProperty("total_results")
    private int totalResults;

    public DesksAvailabilityDTO(List<DeskAvailabilityDTO> results, int totalResults) {
        this.results = results;
        this.totalResults = totalResults;
    }

    public List<DeskAvailabilityDTO> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }
}

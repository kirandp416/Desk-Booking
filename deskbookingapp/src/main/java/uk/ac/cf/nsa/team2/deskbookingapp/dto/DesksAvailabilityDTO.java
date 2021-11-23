package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

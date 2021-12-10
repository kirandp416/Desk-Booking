package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A class that is used to create a JSON of DeskAvailabilityAdminDTO objects. Since
 * DeskAvailabilityAdminDTO are JSON-ified too, this just creates a JSON of JSONs i.e.
 * a JSON.
 */
public class DesksAvailabilityAdminDTO {

    @JsonProperty("results")
    private List<DeskAvailabilityAdminDTO> results; // A collection of desks

    @JsonProperty("total_results")
    private int totalResults; // A count of the total number of desks

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

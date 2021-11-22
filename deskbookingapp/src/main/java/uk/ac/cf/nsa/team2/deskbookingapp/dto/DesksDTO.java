package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A data transfer object for multiple desks with a total results field.
 * <p>
 * This is used where all desks may not be returned in one go i.e.
 * where offset and limit parameters are used.
 * <p>
 * So, along with the desks that are returned, a total results field is also returned
 * stating the total number of desks ignoring offset and limit, so that the client side
 * is aware of how many results are available to consume.
 */
public class DesksDTO {

    @JsonProperty("results")
    private List<DeskDTO> results;

    @JsonProperty("total_results")
    private int totalResults;

    public DesksDTO(List<DeskDTO> results, int totalResults) {
        this.results = results;
        this.totalResults = totalResults;
    }

    public List<DeskDTO> getResults() {
        return results;
    }

    public void setResults(List<DeskDTO> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

}

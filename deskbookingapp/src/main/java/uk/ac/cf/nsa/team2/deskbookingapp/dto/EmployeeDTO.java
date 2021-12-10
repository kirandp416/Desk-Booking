package uk.ac.cf.nsa.team2.deskbookingapp.dto;

/**
 * A data transfer object to transfer data on employees. Since
 * our employee users are currently stored in-memory, this data
 * transfer object will only be used to get the user data from
 * the user table (this user table was manually created to reflect
 * the in-memory users).
 */
public class EmployeeDTO {

    private String username; // The username of the employee

    public EmployeeDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

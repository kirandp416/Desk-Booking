package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;

/**
 * Repository which manages the data for desks in the underlying store.
 */
public interface DeskRepository {

    /**
     * Adds a new desk to the underlying store.
     *
     * @param desk the desk to add.
     * @return true if the operation succeeded, false otherwise.
     */
    boolean add(DeskDTO desk);

}

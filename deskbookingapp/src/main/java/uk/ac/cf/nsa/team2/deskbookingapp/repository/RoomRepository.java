package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;

/**
 * A repository which manages the data for rooms in the underlying store.
 */
public interface RoomRepository {

    /**
     * Adds a new room to the underlying store.
     *
     * @param room The room to add.
     * @return true if the operation succeeded, false otherwise.
     */
    boolean add(RoomDTO room);

}

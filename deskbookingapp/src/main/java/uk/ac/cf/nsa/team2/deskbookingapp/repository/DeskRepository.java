package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;

import java.util.Optional;

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

    /**
     * Checks whether a room has a desk with the specified name.
     *
     * @param roomId   the ID of the room.
     * @param deskName The name of the desk.
     * @return an optional containing a boolean stating whether the room has a desk with the
     * specified name if the operation was successful.
     */
    Optional<Boolean> checkDeskNameExistsForRoom(int roomId, String deskName);

}

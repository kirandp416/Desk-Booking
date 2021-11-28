package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskAvailabilityDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;

import java.util.List;
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

    /**
     * Deletes a desk by ID.
     *
     * @param id the ID of the desk.
     * @return true if the operation succeeded, false otherwise.
     */
    boolean delete(int id);

    /**
     * Checks whether a desk exists by ID.
     *
     * @param id the ID of the desk.
     * @return an optional containing a boolean stating whether the desk exists if the operation was successful.
     */
    Optional<Boolean> exists(int id);

    /**
     * Finds desks by room.
     *
     * @param roomId the ID of the room.
     * @param offset the number of desks to offset by.
     * @param limit  the maximum number of desks to get.
     * @return an optional containing a list of desks if the operation was successful.
     */
    Optional<List<DeskDTO>> findByRoom(int roomId, int offset, int limit);

    /**
     * Counts the number of desks by room.
     *
     * @param roomId the ID of the room.
     * @return An optional containing the count of desks if the operation was successful.
     */
    Optional<Integer> findByRoomCount(int roomId);

    /**
     * Get a list of data objects that represents desks with their availability included
     * in each of those objects. The availability of a desk object is for a particular room,
     * on a particular date, and these are passed as arguments to the function. Offset and
     * limit are also passed, which enables the pagination of the desk objects when they are
     * being rendered.
     *
     * @param roomId The id of the room
     * @param date   The date of the availability that the function caller is interested in
     * @param offset The number of desks to offset by
     * @param limit  The maximum number of desks that should be returned
     */
    Optional<List<DeskAvailabilityDTO>> findByRoomIncludeAvailability(int roomId, String date, int offset, int limit);

}

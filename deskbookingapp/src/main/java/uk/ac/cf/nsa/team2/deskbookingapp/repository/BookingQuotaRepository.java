package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import java.util.Optional;

/**
 * Repository which manages the data for booking quota in the underlying store.
 */
public interface BookingQuotaRepository {

    /**
     * Finds the booking quota for the current month for the specified room.
     *
     * @param roomId the ID of the room.
     * @return an optional containing the quota for the current month
     */
    Optional<Integer> findQuotaByRoom(int roomId);

    /**
     * Finds the booking quota used by an employee for the current month.
     *
     * @param username the employee's username.
     * @return an optional containing the quota used for the month by the employee if the operation was successful.
     */
    Optional<Integer> findUsedQuotaByEmployee(String username);

}

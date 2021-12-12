package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomImgDTO;

import java.util.List;
import java.util.Optional;

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

    boolean addRoomAndImg(RoomImgDTO room);

    /**
     * Gets all rooms from the underlying store.
     *
     * @return an optional containing a list of rooms if the operation was successful.
     */
    Optional<List<RoomDTO>> findAll();
    public boolean deleteRoom(Integer id);
    public Optional<List<RoomDTO>> findById(Integer id);
    public boolean editRoomAjax(RoomDTO dto);
}

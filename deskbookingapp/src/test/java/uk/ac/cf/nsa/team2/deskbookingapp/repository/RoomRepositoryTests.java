package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/database_test.sql")
public class RoomRepositoryTests {
    //Add Room Repository by using Spring
    @Autowired
    private RoomRepository roomRepository;

    //Add rooms as a List
    private static List<RoomDTO> rooms;

    @BeforeAll
    public static void beforeTesting() {
        // Create test rooms.
        RoomDTO room1 = new RoomDTO(1, "Room 1");
        RoomDTO room2 = new RoomDTO(2, "Room 2");
        RoomDTO room3 = new RoomDTO(3, "Room 23");

        // Add  test rooms to the database.
        rooms = new ArrayList<>(4);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

    }

    private List<Boolean> addRooms() {
        List<Boolean> results = new ArrayList<>();

        // Add test rooms.
        for (RoomDTO room : rooms) {
            boolean result = roomRepository.add(room);
            results.add(result);
        }

        return results;
    }

    /**
     * Testing method findByID(Integer Id)
     * This test fetches the details of a Room from database
     * by using particular Id
     */
    @Test
    public void whenFindingSpecificRoom_ThenTestRoomIsInResults() {
        // Add rooms.
        List<Boolean> addRoomsResults = addRooms();

        // Get room by Id.
        Optional<List<RoomDTO>> oneRoom = roomRepository.findById(3);

        // If optional is empty, query failed.
        if (oneRoom.isEmpty()) {
            fail("Query to find room from the database failed");
        }

        // Assert to check if the query worked.
        assertEquals("Room 23", rooms.get(2).getName());

    }

    /**
     * Testing deleteRoom(Integer id)
     * This tests the deletion of a room when a
     * specific Id is passed.
     */
    @Test
    public void whenDeletingAnExistingRoom_ThenDeleteIsSuccessful() {
        // Add rooms.
        List<Boolean> addRoomsResults = addRooms();

        // Delete the room by passing any Id
        roomRepository.deleteRoom(1);
        // Get rooms.
        Optional<List<RoomDTO>> allRooms = roomRepository.findAll();

        // Stream to get Id of rooms.
        List<Integer> roomId = allRooms.get().stream()
                .map(RoomDTO::getId)
                .collect(Collectors.toList());

        // Assert that the deleted room's Id is not in the results.
        assertFalse(roomId.contains(rooms.get(0).getId()));
    }

    @Test
    public void whenEditingNameOfExistingRoom_ThenUpdateIsSuccessful() {
        List<Boolean> results = addRooms();
        String roomName = "Room 3";
        int id = 2;
        RoomDTO room4 = new RoomDTO();
        room4.setId(id);
        room4.setName(roomName);
        // Editing the name of room 23
        boolean result = roomRepository.editRoomAjax(room4);
        if (!result) {
            fail("Query to delete room failed");
        }

        // Get room by Id.
        Optional<List<RoomDTO>> oneRoom = roomRepository.findById(2);

        // Assert to check if the query worked.
        assertEquals(roomName, oneRoom.get().get(0).getName());


    }

}


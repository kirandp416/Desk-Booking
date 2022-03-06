//package uk.ac.cf.nsa.team2.deskbookingapp.repository;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Tests for the {@link RoomMySqlJdbcRepository} class.
// */
//@SpringBootTest
//@Sql("/database_test.sql")
//public class RoomMySqlJdbcRepositoryTests {
//
//    // Test rooms.
//    private static List<RoomDTO> testRooms;
//
//    // Room repository injected by Spring.
//    @Autowired
//    private RoomRepository roomRepository;
//
//    /**
//     * Initialisation logic to be run once before all tests are executed.
//     */
//    @BeforeAll
//    public static void beforeAll() {
//        // Create test rooms.
//        RoomDTO testRoom1 = new RoomDTO(1, "Room 1");
//        RoomDTO testRoom2 = new RoomDTO(2, "Room 2");
//
//        // Initialise list and add rooms.
//        testRooms = new ArrayList<>(2);
//        testRooms.add(testRoom1);
//        testRooms.add(testRoom2);
//    }
//
//    /**
//     * Tests adding rooms to the database.
//     */
//    @Test
//    public void whenAddingRoom_ThenResultIsSuccessful() {
//        // Add rooms.
//        List<Boolean> results = addRooms();
//
//        // Assert.
//        assertFalse(results.contains(false));
//    }
//
//    /**
//     * Tests finding all rooms in the database.
//     */
//    @Test
//    public void whenFindingRooms_ThenTestRoomsAreInResults() {
//        // Add rooms.
//        List<Boolean> addRoomsResults = addRooms();
//
//        // Fail the test if adding failed.
//        if (addRoomsResults.contains(false)) {
//            fail("Failed to add rooms to the database");
//        }
//
//        // Get rooms.
//        Optional<List<RoomDTO>> rooms = roomRepository.findAll();
//
//        // If optional is empty, query failed.
//        if (rooms.isEmpty()) {
//            fail("Query to find all rooms from the database failed");
//        }
//
//        // Stream to get names of rooms.
//        List<String> roomNames = rooms.get().stream()
//                .map(RoomDTO::getName)
//                .collect(Collectors.toList());
//
//        // Stream to get names of test rooms.
//        List<String> testRoomNames = testRooms.stream()
//                .map(RoomDTO::getName)
//                .collect(Collectors.toList());
//
//        // Assert.
//        assertTrue(roomNames.containsAll(testRoomNames));
//    }
//
//    /**
//     * Tests deleting a room from the database.
//     * The deleted room should not be in the records found after deletion.
//     */
//    @Test
//    public void whenDeletingAnExistingRoom_ThenRoomIsNotInResults() {
//        // Add rooms.
//        List<Boolean> addRoomsResults = addRooms();
//
//        // Fail the test if adding failed.
//        if (addRoomsResults.contains(false)) {
//            fail("Failed to add rooms to the database");
//        }
//
//        // Delete room.
//        boolean result = roomRepository.deleteRoom(testRooms.get(0).getId());
//
//        // Fail the test if the deletion failed.
//        if (!result) {
//            fail("Query to delete room failed");
//        }
//
//        // Get rooms.
//        Optional<List<RoomDTO>> rooms = roomRepository.findAll();
//
//        // If optional is empty, query failed.
//        if (rooms.isEmpty()) {
//            fail("Query to find all rooms from the database failed");
//        }
//
//        // Stream to get names of rooms.
//        List<String> roomNames = rooms.get().stream()
//                .map(RoomDTO::getName)
//                .collect(Collectors.toList());
//
//        // Assert that the deleted room's name is not in the results.
//        assertFalse(roomNames.contains(testRooms.get(0).getName()));
//    }
//
//
//    /**
//     * Adds rooms to the database.
//     *
//     * @return an array of booleans stating whether the operations succeeded.
//     */
//    private List<Boolean> addRooms() {
//        List<Boolean> results = new ArrayList<>();
//
//        // Add test rooms.
//        for (RoomDTO room : testRooms) {
//            boolean result = roomRepository.add(room);
//            results.add(result);
//        }
//
//        return results;
//    }
//
//}

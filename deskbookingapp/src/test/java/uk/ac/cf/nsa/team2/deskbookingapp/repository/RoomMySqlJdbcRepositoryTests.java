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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the {@link RoomMySqlJdbcRepository} class.
 */
@SpringBootTest
public class RoomMySqlJdbcRepositoryTests {

    // Test rooms.
    private static List<RoomDTO> testRooms;

    // Room repository injected by Spring.
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Initialisation logic to be run once before all tests are executed.
     * As well as creating test room objects, a SQL script is executed to create a test database.
     */
    @BeforeAll
    @Sql("/database_test.sql")
    public static void beforeAll() {
        // Create test rooms.
        RoomDTO testRoom1 = new RoomDTO();
        testRoom1.setName("Room 1");
        RoomDTO testRoom2 = new RoomDTO();
        testRoom2.setName("Room 2");

        // Initialise list and add rooms.
        testRooms = new ArrayList<>(2);
        testRooms.add(testRoom1);
        testRooms.add(testRoom2);
    }

    /**
     * Tests adding rooms to the database.
     */
    @Test
    public void whenAddingRoom_ThenResultIsSuccessful() {
        // Add test rooms to database.
        for (RoomDTO room : testRooms) {
            boolean result = roomRepository.add(room);
            // Fail the test if adding failed.
            if (!result) {
                fail("Query to add a room to the database failed");
            }
        }
    }

    /**
     * Tests finding all rooms in the database.
     */
    @Test
    public void whenFindingRooms_ThenTestRoomsAreInResults() {
        // Get rooms.
        Optional<List<RoomDTO>> rooms = roomRepository.findAll();

        // If optional is empty, query failed.
        if (rooms.isEmpty()) {
            fail("Query to find all rooms from the database failed");
        }

        // Stream to get names of rooms.
        List<String> roomNames = rooms.get().stream()
                .map(RoomDTO::getName)
                .collect(Collectors.toList());

        // Stream to get names of test rooms.
        List<String> testRoomNames = testRooms.stream()
                .map(RoomDTO::getName)
                .collect(Collectors.toList());

        // Assert.
        assertTrue(roomNames.containsAll(testRoomNames));
    }

}

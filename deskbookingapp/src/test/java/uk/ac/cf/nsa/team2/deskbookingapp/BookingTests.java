package uk.ac.cf.nsa.team2.deskbookingapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test all Java classes, interfaces and their methods that
 * are directly related to the booking table in the database.
 */
public class BookingTests {

    // Create fields for items we would like to test

    private static BookingDTO bookingDTO;

    // Set up instances for testing

    @BeforeAll
    public static void before(){
        bookingDTO = new BookingDTO(1, "2000-01-01", "Room 1", "Desk 1", "Standard", "Everyone's favourite desk.");
    }

    // BookingDTO Tests

    // Test BookingDTO's getters to see that they return the expected data

    @Test
    public void whenGettingDeskIdThenReturnCorrectId(){
        assertEquals(1, bookingDTO.getId());
    }

    @Test
    public void whenGettingDateThenReturnCorrectDate(){
        assertEquals("2000-01-01", bookingDTO.getDate());
    }

    @Test
    public void whenGettingRoomNameThenReturnCorrectRoomName(){
        assertEquals("Room 1", bookingDTO.getRoomName());
    }

    @Test
    public void whenGettingDeskNameThenReturnCorrectDeskName(){
        assertEquals("Desk 1", bookingDTO.getDeskName());
    }

    @Test
    public void whenGettingDeskTypeThenReturnCorrectDeskType(){
        assertEquals("Standard", bookingDTO.getDeskType());
    }

    @Test
    public void whenGettingDeskNotesThenReturnCorrectDeskNotes(){
        assertEquals("Everyone's favourite desk.", bookingDTO.getDeskNotes());
    }

    @Test
    public void whenGettingDateOrderedForDisplayThenReturnCorrectDateOrderedForDisplay(){
        assertEquals("01-01-00", bookingDTO.getDateOrderedForDisplay());
    }

    // Test dateReOrderer() method with some valid dummy data to test that it
    // performs intended reformatting of a valid date.

    @Test
    public void whenGettingReOrderedDateThenReturnsDesiredFormatOfDate(){
        assertEquals("15-12-00", bookingDTO.dateReOrderer("2000-12-15"));
    }

    // Test dateReOrderer() method with some invalid dummy data to test that it
    // does not throw any exceptions when passed an invalid string as the argument.

    @Test
    public void whenPassingNonDateStringThenDoesNotThrowException(){
        assertDoesNotThrow(()-> bookingDTO.dateReOrderer("hello"));
    }

}

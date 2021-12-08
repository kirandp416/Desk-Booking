package uk.ac.cf.nsa.team2.deskbookingapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test all Java classes, interfaces and methods that
 * are directly related to the booking table in the database.
 */
public class BookingTests {

    // Create fields for items we would like to test

    private static BookingDTO bookingDTO;


    /**
     * Instantiate a new BookingDTO object with some valid data for testing
     */
    @BeforeAll
    public static void before(){
        bookingDTO = new BookingDTO(1, "2000-01-01", "Room 1", "Desk 1", "Standard", "Everyone's favourite desk.");
    }

    // BookingDTO Tests

    /**
     * Test that calling the getId method on the BookingDTO instance, returns
     * a value that is equal to the value of the id that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingDeskIdThenReturnCorrectId(){
        assertEquals(1, bookingDTO.getId());
    }

    /**
     * Test that calling the getDate method on the BookingDTO instance, returns
     * a value that is equal to the value of the date that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingDateThenReturnCorrectDate(){
        assertEquals("2000-01-01", bookingDTO.getDate());
    }

    /**
     * Test that calling the getRoomName method on the BookingDTO instance, returns
     * a value that is equal to the value of the roomName that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingRoomNameThenReturnCorrectRoomName(){
        assertEquals("Room 1", bookingDTO.getRoomName());
    }

    /**
     * Test that calling the getDeskName method on the BookingDTO instance, returns
     * a value that is equal to the value of the deskName that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingDeskNameThenReturnCorrectDeskName(){
        assertEquals("Desk 1", bookingDTO.getDeskName());
    }

    /**
     * Test that calling the getDeskType method on the BookingDTO instance, returns
     * a value that is equal to the value of the deskType that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingDeskTypeThenReturnCorrectDeskType(){
        assertEquals("Standard", bookingDTO.getDeskType());
    }

    /**
     * Test that calling the getDeskNotes method on the BookingDTO instance, returns
     * a value that is equal to the value of the deskNotes that we used to instantiate
     * that object.
     */
    @Test
    public void whenGettingDeskNotesThenReturnCorrectDeskNotes(){
        assertEquals("Everyone's favourite desk.", bookingDTO.getDeskNotes());
    }

    /**
     * Test that calling the getDateOrderedForDisplay method on the BookingDTO instance,
     * returns a value that is equal to the date that we used to instantiate that object
     * (but formatted in the way we decided to format dates in dateReorderer method i.e.
     * dd-mm-yy).
     */
    @Test
    public void whenGettingDateOrderedForDisplayThenReturnCorrectDateOrderedForDisplay(){
        assertEquals("01-01-00", bookingDTO.getDateOrderedForDisplay());
    }


    /**
     * For completeness, actually test the dateReOrderer method to show that it
     * takes a value that represents a date with format yyyy-mm-dd and returns a
     * value equal to that but with format dd-mm-yy.
     */
    @Test
    public void whenGettingReOrderedDateThenReturnsDesiredFormatOfDate(){
        assertEquals("15-12-00", bookingDTO.dateReOrderer("2000-12-15"));
    }

    /**
     * Show that in one particular scenario when the dateReOrderer method is passed a
     * string that is not in the format yyyy-mm-dd (where y, m and d are all integers),
     * that the method does not throw any exceptions.
     */
    @Test
    public void whenPassingNonDateStringThenDoesNotThrowException(){
        assertDoesNotThrow(()-> bookingDTO.dateReOrderer("hello"));
    }

}

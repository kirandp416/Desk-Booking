//package uk.ac.cf.nsa.team2.deskbookingapp;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
///**
// * A class to test all Java classes, interfaces and methods that
// * are directly related to the booking table in the database.
// */
//public class BookingTests {
//
//    // Create fields for items we would like to test
//
//    private static BookingDTO bookingDTO;
//
//
//    /**
//     * Instantiate a new BookingDTO object with some valid data for testing
//     * References: https://stackoverflow.com/questions/18915075/java-convert-string-to-timestamp
//     */
//    @BeforeAll
//    public static void before() {
//
//        try {
//
//            // Attempt to create valid OffsetDateTime object
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date parsedDate = dateFormat.parse("2021-11-22 12:15:00");
//            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//            OffsetDateTime offsetDateTime = timestamp.toInstant().atOffset(ZoneOffset.UTC);
//            System.out.println("Printing offsetDateTime from before method...");
//            System.out.println(offsetDateTime);
//
//            // Use the OffsetDateTime object (and other valid data) to create a new
//            // BookingDTO object
//
//            bookingDTO = new BookingDTO(1, "user1", "2000-01-01", "Room 1", "Desk 1", "Standard", "Everyone's favourite desk.", offsetDateTime);
//        } catch (Exception e) {
//            System.out.println("TimeStamp instantiation failed in before method");
//        }
//
//    }
//
//    // BookingDTO Tests
//
//    /**
//     * Test that calling the getId method on the BookingDTO instance, returns
//     * a value that is equal to the value of the id that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingDeskIdThenReturnCorrectId() {
//
//        OffsetDateTime offsetDT8 = OffsetDateTime.parse("2019-08-31T15:20:30+08:00");
//
//        assertEquals(1, bookingDTO.getId());
//    }
//
//    /**
//     * Test that calling the getDate method on the BookingDTO instance, returns
//     * a value that is equal to the value of the date that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingDateThenReturnCorrectDate() {
//        assertEquals("2000-01-01", bookingDTO.getDate());
//    }
//
//    /**
//     * Test that calling the getRoomName method on the BookingDTO instance, returns
//     * a value that is equal to the value of the roomName that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingRoomNameThenReturnCorrectRoomName() {
//        assertEquals("Room 1", bookingDTO.getRoomName());
//    }
//
//    /**
//     * Test that calling the getDeskName method on the BookingDTO instance, returns
//     * a value that is equal to the value of the deskName that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingDeskNameThenReturnCorrectDeskName() {
//        assertEquals("Desk 1", bookingDTO.getDeskName());
//    }
//
//    /**
//     * Test that calling the getDeskType method on the BookingDTO instance, returns
//     * a value that is equal to the value of the deskType that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingDeskTypeThenReturnCorrectDeskType() {
//        assertEquals("Standard", bookingDTO.getDeskType());
//    }
//
//    /**
//     * Test that calling the getDeskNotes method on the BookingDTO instance, returns
//     * a value that is equal to the value of the deskNotes that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingDeskNotesThenReturnCorrectDeskNotes() {
//        assertEquals("Everyone's favourite desk.", bookingDTO.getDeskNotes());
//    }
//
//    /**
//     * Test that calling the getTimestamp method on the BookingDTO instance, returns
//     * a value that is equal to the value of the offsetDateTime that we used to instantiate
//     * that object.
//     */
//    @Test
//    public void whenGettingOffsetDateTimeThenReturnCorrectOffsetDateTime() {
//        try {
//
//            // Attempt to create a valid OffSetDateTime object
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date parsedDate = dateFormat.parse("2021-11-22 12:15:00");
//            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//            OffsetDateTime offsetDateTime = timestamp.toInstant().atOffset(ZoneOffset.UTC);
//            System.out.println("Printing offsetDateTime from test method...");
//            System.out.println(offsetDateTime);
//
//            // Compare that OffSetDateTime instance to what getTimestamp() returns
//
//            assertEquals(offsetDateTime, bookingDTO.getTimestamp());
//
//        } catch (Exception e) {
//            System.out.println("OffsetDateTime test never reached, exception thrown.");
//        }
//
//    }
//
//    /**
//     * Test that calling the getDateOrderedForDisplay method on the BookingDTO instance,
//     * returns a value that is equal to the date that we used to instantiate that object
//     * (but formatted in the way we decided to format dates in dateReorderer method i.e.
//     * dd-mm-yy).
//     */
//    @Test
//    public void whenGettingDateOrderedForDisplayThenReturnCorrectDateOrderedForDisplay() {
//        assertEquals("01-01-00", bookingDTO.getDateOrderedForDisplay());
//    }
//
//
//    /**
//     * For completeness, actually test the dateReOrderer method to show that it
//     * takes a value that represents a date with format yyyy-mm-dd and returns a
//     * value equal to that but with format dd-mm-yy.
//     */
//    @Test
//    public void whenGettingReOrderedDateThenReturnsDesiredFormatOfDate() {
//        assertEquals("15-12-00", bookingDTO.dateReOrderer("2000-12-15"));
//    }
//
//    /**
//     * Show that in one particular scenario when the dateReOrderer method is passed a
//     * string that is not in the format yyyy-mm-dd (where y, m and d are all integers),
//     * that the method does not throw any exceptions.
//     */
//    @Test
//    public void whenPassingNonDateStringThenDoesNotThrowException() {
//        assertDoesNotThrow(() -> bookingDTO.dateReOrderer("hello"));
//    }
//
//}

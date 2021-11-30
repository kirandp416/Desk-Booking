package uk.ac.cf.nsa.team2.deskbookingapp.dto;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingDTOTest {

    private static BookingDTO bookingDTO;

    @BeforeAll
    public static void before(){
        bookingDTO = new BookingDTO(1, "2000-01-01", "Room 1", "Desk 1", "Standard", "Everyone's favourite desk.");
    }

    @Test
    public void whenGettingDeskIdThenReturnCorrectId(){
        assertEquals(1, bookingDTO.getId());
    }
}

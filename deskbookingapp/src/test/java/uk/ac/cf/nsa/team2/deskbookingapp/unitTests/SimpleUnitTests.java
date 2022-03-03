package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SimpleUnitTests {
    @Test
    void roomDTOTest(){
        RoomDTO room1 = new RoomDTO(1,"Cardiff");
        assertEquals(room1.getId(),1);
        assertEquals(room1.getName(),"Cardiff");
    }

}

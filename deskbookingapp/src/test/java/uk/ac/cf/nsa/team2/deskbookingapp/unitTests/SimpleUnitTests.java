package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;
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
    @Test
    void deskDTOTest(){
        DeskTypeDTO deskType1 = new DeskTypeDTO(1,"Standing");
        DeskDTO desk1 = new DeskDTO(1,1,deskType1,"Corner Desk","Normal");
        assertEquals(deskType1.getName(),"Standing");
        assertEquals(desk1.getDeskType(),deskType1);
        assertEquals(desk1.getName(),"Corner Desk");
        assertEquals(desk1.getNotes(),"Normal");

    }
}

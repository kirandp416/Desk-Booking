package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.nsa.team2.deskbookingapp.controllers.RoomAdminController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(RoomAdminController.class)
public class MockMVCSecurityTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void Login() throws Exception {
        this.mockMvc.perform(get("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "admin")
                        .param("password", "admin")
                        )
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void addRoom() throws Exception {

        this.mockMvc.perform(get("/admin/room/add")
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andDo(print()) .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Room")));
    }
    @Test
    public void addRoomUnauthorised() throws Exception {

        this.mockMvc.perform(get("/admin/room/add"))
                .andDo(print()) .andExpect(status().is(302));
    }
    @Test
    public void manageRooms() throws Exception {

        RoomDTO room1 = new RoomDTO( 1,"Canada");

        given(this.roomRepository.findAll()).willReturn(Optional.of(Arrays.asList(room1)));
        this.mockMvc.perform(get("/admin/rooms/all")
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andDo(print()) .andExpect(status().is(200));
    }
    @Test
    public void deleteDesk() throws Exception {

        RoomDTO room2 = new RoomDTO( 2,"Canada");

        given(this.roomRepository.deleteRoom(room2.getId())).willReturn(false);
        this.mockMvc.perform(delete("/admin/room/delete")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("id", "2"))
                        .andDo(print()) .andExpect(status().is(302));
    }
    @Test
    public void editRoom() throws Exception {
        RoomDTO room3 = new RoomDTO( 3,"Canada");

        given(this.roomRepository.editRoomAjax(room3)).willReturn(true);

        this.mockMvc.perform(put("/admin/room/edit")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "4")
                        .param("name", "Boomtown Towers"))
                .andDo(print())
                .andExpect(status().is(302));

    }

}



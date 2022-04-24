package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        this.mockMvc.perform(get("http://localhost:8080/login")
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

//    @Test
//    public void addItem() throws Exception {
////        RoomDTO room5 = new RoomDTO( 2,"London");
//
//        this.mockMvc.perform(get("/admin/rooms/all")
//                        .with(user("admin").password("admin").roles("ADMIN")))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("London")));
//    }
}



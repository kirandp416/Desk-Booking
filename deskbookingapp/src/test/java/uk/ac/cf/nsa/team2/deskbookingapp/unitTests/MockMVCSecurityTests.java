package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.nsa.team2.deskbookingapp.controllers.RoomAdminController;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomAdminController.class)
public class MockMVCSecurityTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void addRoom() throws Exception {
        this.mockMvc.perform(get("/admin/room/add")
                        .with(user("admin").password("{noop}admin").roles("ADMIN")))
                .andDo(print()) .andExpect(status().isOk())
               ;
    }
}

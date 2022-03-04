package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.nsa.team2.deskbookingapp.controllers.RoomAdminController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomAdminController.class)
class MockMVCTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void loginScreen() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Username")));
    }
    @Test
    public void homeScreen() throws Exception{
        this.mockMvc.perform(get("/Home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")));
    }
}

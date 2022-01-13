package uk.ac.cf.nsa.team2.deskbookingapp;

import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingCommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.CommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.impl.CommentRepositoryImpl;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.Snowflake;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2022/1/13
 */
@AutoConfigureMockMvc
@SpringBootTest
class YitingUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CommentRepositoryImpl commentRepository;

    @Before
    private void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * perform：execute a RequestBuilder request
     * get:Declare a method to send a get request。MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)
     * urlTemplate – a URL template; the resulting URL will be encoded uriVars – zero or more URI variables。
     * In addition, other methods of requesting are provided，such as：post、put、delete.....。
     * param：add request parameters
     * content：add requestBaby
     * andExpect：add ResultMatcher validation rule
     * andReturn：return the corresponding MvcResult
     *
     * @throws Exception
     */
    @Transactional()
    @Rollback()
    @Test
    /**
     * REST API test
     */
    public void testCommentRestController() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/comment/fetchAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);

        // test "/comment/fetchBooking"
        String userName = "user1";
        mockMvc.perform(MockMvcRequestBuilders.get("/comment/fetchBooking")
                .param("username", userName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn().getResponse().getContentAsString();

        // test "/comment/add"
        Map<String, Object> map = new HashMap<>();
        map.put("roomId", 1);
        mockMvc.perform(MockMvcRequestBuilders.post("/comment/add")
                .content(JSONObject.toJSONString(map))
                .contentType(MediaType.APPLICATION_JSON));

        // test "/comment/delete/{id}"
        mockMvc.perform(MockMvcRequestBuilders.delete("/comment/delete/920945134139342848"));

    }

    @Test
    void commentRepositoryTest() {
        // findAll() method test
        List<CommentDTO> allList = commentRepository.findAll();
        // assert
        Assert.isTrue(allList instanceof List, "list");
        // assert
        Assert.isTrue(allList.size() >= 0, "not exp");
        // getOwnBooking() method test
        List<BookingCommentDTO> ownBooking = commentRepository.getOwnBooking("user1", 0, 10);
        // assert
        Assert.isTrue(ownBooking instanceof List, "list");
        // assert
        Assert.isTrue(ownBooking.size() >= 0, "not exp");
    }

    @Resource
    private Snowflake snowflake;

    @Test
    public void testSnowflake() {
        Long id;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            id = snowflake.nextId();
            set.add(id);
        }
        Assert.isTrue(set.size() == 1000000, "error");
        Long id1 = set.iterator().next();
        System.out.println(id1);
        int count = 0;
        while (id1 > 0) {
            count += 1;
            id1 = (id1 / 10);
        }
        // assert
        Assert.isTrue(count == 18, "error");
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.DeskRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * A REST API controller for desks.
 */
@Controller
public class DeskRestController {

    private final DeskRepository deskRepository;

    /**
     * Initialises a new instance of the controller.
     *
     * @param deskRepository a desk repository injected by Spring.
     */
    @Autowired
    public DeskRestController(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    /**
     * Route to get desks, optionally for a specific room.
     *
     * @param roomId   the ID of the room.
     * @param offset   the number of desks to offset by.
     * @param limit    the maximum number of desks to get.
     * @param response the HTTP response.
     * @return A JSON array of desks.
     */
    @GetMapping(path = "/api/desks", produces = "application/json")
    public List<DeskDTO> getDesks(@RequestParam("room_id") int roomId, @RequestParam("offset") int offset,
                                  @RequestParam("limit") int limit, HttpServletResponse response) {
        // Query for desks.
        Optional<List<DeskDTO>> desks = deskRepository.findByRoom(roomId, offset, limit);

        // Return 500 status if there was an error.
        if (desks.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        return desks.get();
    }

}

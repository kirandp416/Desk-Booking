package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.json.AddDeskJsonRequest;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.DeskRepository;

import javax.servlet.http.HttpServletResponse;

/**
 * A REST API controller for managing desks as an admin.
 */
@RestController
public class DeskAdminRestController {

    private final DeskRepository deskRepository;

    /**
     * Initialises a new instance of the controller.
     *
     * @param deskRepository A desk repository injected by Spring.
     */
    @Autowired
    public DeskAdminRestController(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    /**
     * Route to create a desk.
     *
     * @param request  the JSON request.
     * @param response the HTTP response.
     */
    @PostMapping("/api/admin/desks")
    public void createDesk(@RequestBody AddDeskJsonRequest request, HttpServletResponse response) {
        // Create DTO.
        DeskDTO dto = new DeskDTO();
        dto.setRoomId(request.getRoom());
        dto.setName(request.getName());

        // Create desk.
        boolean successful = deskRepository.add(dto);

        // Set status to 500 internal if unsuccessful.
        if (!successful) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        // Set status to 201 created.
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}

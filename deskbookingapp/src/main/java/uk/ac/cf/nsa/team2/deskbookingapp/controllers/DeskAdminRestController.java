package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.json.AddDeskJsonRequest;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.DeskRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
        // Query if a desk with the same name exists for the room.
        Optional<Boolean> deskExists = deskRepository.checkDeskNameExistsForRoom(request.getRoom(), request.getName());

        // If optional is empty, return a 500 internal status.
        if (deskExists.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // If desk exists, return a 409 conflict status.
        if (deskExists.get()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        // Create DTO.
        DeskDTO deskDTO = new DeskDTO();
        deskDTO.setRoomId(request.getRoom());
        deskDTO.setName(request.getName());
        deskDTO.setNotes(request.getNotes());

        DeskTypeDTO deskTypeDTO = new DeskTypeDTO();
        deskTypeDTO.setId(request.getDeskType());
        deskDTO.setDeskType(deskTypeDTO);

        // Create desk.
        boolean successful = deskRepository.add(deskDTO);

        // Return 500 internal status if unsuccessful.
        if (!successful) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Set status to 201 created.
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}

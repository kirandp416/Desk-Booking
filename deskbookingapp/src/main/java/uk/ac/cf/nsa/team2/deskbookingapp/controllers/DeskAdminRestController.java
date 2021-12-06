package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        deskTypeDTO.setDeskTypeId(request.getDeskType());
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

    /**
     * Route to delete a desk.
     *
     * @param id       the ID of the desk.
     * @param response the HTTP response.
     */
    @DeleteMapping("/api/admin/desks/{id}")
    public void deleteDesk(@PathVariable("id") int id, HttpServletResponse response) {
        // Query to check if desk exists.
        Optional<Boolean> exists = deskRepository.exists(id);

        // Return 500 status if there was an error (optional is empty).
        if (exists.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Return 404 status if the desk does not exist.
        if (!exists.get()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Delete desk.
        boolean deleteResult = deskRepository.delete(id);

        // Return 500 if there was an error.
        if (!deleteResult) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Return 204 status.
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}

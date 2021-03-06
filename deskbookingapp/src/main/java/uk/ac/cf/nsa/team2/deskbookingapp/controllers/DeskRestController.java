package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.*;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.DeskRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * A REST API controller for desks.
 */
@RestController
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
    public DesksDTO getDesks(@RequestParam("room_id") int roomId, @RequestParam("offset") int offset,
                             @RequestParam("limit") int limit, HttpServletResponse response) {
        // Query for desks and count of desks.
        Optional<List<DeskDTO>> desks = deskRepository.findByRoom(roomId, offset, limit);
        Optional<Integer> desksCount = deskRepository.findByRoomCount(roomId);

        // Return 500 status if there was an error.
        if (desks.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        if (desksCount.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        return new DesksDTO(desks.get(), desksCount.get());
    }


    /**
     * Route that takes username, roomId and date and gets a collection of desk-like-objects.
     * The desk like objects also store whether they are available on that day and whether
     * the currently logged-in user has any desk bookings on that day (in any room). If one
     * or more desks are retrieved from the get then the desk-like-objects are passed to
     * DesksAvailability constructor. This will produce a JSONified version of the desk
     * collection. That object is returned.
     * @param roomId The room for the desks
     * @param date The date the user is interested in booking a desk
     * @param offset A parameter to allow for pagination
     * @param limit Another paramter to allow for pagination
     * @param response the http response coming in
     * @return
     */
    @GetMapping(path = "/api/desks_available", produces = "application/json")
    public DesksAvailabilityDTO getDesksAvailability(@RequestParam("username") String username, @RequestParam("room_id") int roomId, @RequestParam("date") String date, @RequestParam("offset") int offset,
                             @RequestParam("limit") int limit, HttpServletResponse response) {

        // Query for desks and count of desks.
        Optional<List<DeskAvailabilityDTO>> desks = deskRepository.findByRoomIncludeAvailability(username, roomId, date, offset, limit);
        Optional<Integer> desksCount = deskRepository.findByRoomCount(roomId);

        // Return 500 status if there was an error.
        if (desks.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        if (desksCount.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        return new DesksAvailabilityDTO(desks.get(), desksCount.get());
    }

    /**
     * Route for admin to get a collection of desk-like objects. The desk-like objects
     * store whether they are available on the date that is passed as one of the parameters.
     * If they are not available, they also store the username of the person who booked them
     * on that date and the booking id for the booking. All of these desk-like objects are then
     * passed to the DesksAvailabilityAdminDTO constructor and returned, which effectively
     * returns a JSON of all the desk-like objects.
     * @param roomId The id of the room where the admin is looking to get desks from
     * @param date The date the admin is interested in for getting desks
     * @param offset The first desk that will be displayed in the current page in the pagination
     * @param limit The number of desks to get per page in pagination
     * @param response Object holding HTTP data
     * @return DesksAvailabilityAdminDTO object
     */
    @RequestMapping(path="/api/desks_available_admin", produces= "application/json")
    public DesksAvailabilityAdminDTO getDesksAvailabilityForAdmin(@RequestParam("room_id") int roomId, @RequestParam("date") String date, @RequestParam("offset") int offset,
                                                                  @RequestParam("limit") int limit, HttpServletResponse response){

        // Query for desks and count of desks.
        Optional<List<DeskAvailabilityAdminDTO>> desks = deskRepository.findByRoomIncludeAvailabilityForAdmin(roomId, date, offset, limit);
        Optional<Integer> desksCount = deskRepository.findByRoomCount(roomId);

        // Return 500 status if there was an error.
        if (desks.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        if (desksCount.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        return new DesksAvailabilityAdminDTO(desks.get(), desksCount.get());

    }


}

package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.RoomForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RoomAdminController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomAdminController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Route to get the admin page for adding a room.
     *
     * @return A model and view object.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin/room/add")
    public ModelAndView addRoomPage() {
        return new ModelAndView("/admin/add_room");
    }

    /**
     * Route to process form to add a room.
     *
     * @param form The form data.
     * @return A model and view object.
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/room/add/process_form")
    public ModelAndView addRoomProcessForm(RoomForm form) {
        // Create DTO and add room to repository store.
        RoomDTO dto = new RoomDTO();
        dto.setName(form.getName());
        boolean result = roomRepository.add(dto);

        // Return a model and view, passing in the result of the operation to the view.
        return new ModelAndView("admin/add_room")
                .addObject("result", result);
    }
    /**
     * Create a Model and View object that contains all of the rooms
     * that have been made by a particular user in the Model and the
     * rooms page as the View
     *
     * @return a Model and View object
     */

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/admin/rooms/all", method = RequestMethod.GET)
    public ModelAndView getAllRooms() {
            // Get rooms from repository.

            Optional<List<RoomDTO>> rooms = roomRepository.findAll();
            // If the optional is empty, redirect user to server error page.

            if (rooms.isEmpty()) {
                return new ModelAndView("redirect:/internal_server_error");
            }

            // Return a model and view for the manage desks page,
            // passing the rooms into the view.

            return new ModelAndView("admin/manage_rooms")
                    .addObject("rooms", rooms.get());
    }

}

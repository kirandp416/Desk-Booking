package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

/**
 * A controller for routes to manage desks by an admin.
 */
@Controller
public class DeskAdminController {

    private final RoomRepository roomRepository;

    /**
     * Initialises a new instance of the controller.
     *
     * @param roomRepository a room repository injected by Spring.
     */
    @Autowired
    public DeskAdminController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Route to get the add desk page.
     *
     * @return a ModelAndView object.
     */
    @GetMapping("/admin/add_desk")
    public ModelAndView addDeskPage() {
        // Get rooms from repository.
        Optional<List<RoomDTO>> rooms = roomRepository.findAll();

        // If the optional is empty, redirect user to server error page.
        if (rooms.isEmpty()) {
            return new ModelAndView("redirect:/internal_server_error");
        }

        // Return a model and view for the add desk page,
        // passing the rooms into the view.
        return new ModelAndView("admin/add_desk")
                .addObject("rooms", rooms.get());
    }

}

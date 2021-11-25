package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    /**
     * Create route that will attempt to delete a booking from the booking
     * database, by booking id. If it is successful you will see a view that
     * says successful and if it is not you will see a view that says it failed.
     * We will be calling this method via AJAX so you will not see these views.
     * However, if you would like to see the views and test it, please change
     * request method below to GET and try the route with a valid id in the
     * address bar of browser.
     *
     * @param id the Booking id
     * @return ModelAndView object with a view that will tell you if deletion
     * was a success.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/admin/room/delete", method = RequestMethod.DELETE)
    public ModelAndView roomDelete(@RequestParam(value = "id", defaultValue = "null") String id) {

        ModelAndView mav = new ModelAndView();

        if (!id.equals("null")) {
            Integer idInt = Integer.valueOf(id);
            if (roomRepository.deleteRoom(idInt)) {
                mav.setViewName("admin/RoomDeleteSuccess");
            } else {
                mav.setViewName("admin/RoomDeleteFail");
            }
        } else {
            mav.setViewName("admin/RoomDeleteFail");
        }

        return mav;

    }

}

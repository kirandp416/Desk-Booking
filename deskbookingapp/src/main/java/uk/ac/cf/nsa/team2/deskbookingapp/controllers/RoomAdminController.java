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
     * Create route that will attempt to delete a room from the Rooms
     * database, by room id. If it is successful you will see a view that
     * says successful and if it is not you will see a internal server error.
     *
     * @param id the room id
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
                mav.setViewName("admin/manage_rooms");
            } else {
                mav.setViewName("redirect:/internal_server_error");
            }
        } else {
            mav.setViewName("redirect:/internal_server_error");
        }

        return mav;

    }

    /**
     * Creating a route that will take the id from the row
     * and fetch room details from repo by room_id.
     * If successful you will be redirected to editRoom.
     * Failure will take you to internal_server_error
     * @param id the room id
     * @return Model and View that will redirect to edit page.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/admin/room/edit/{id}")
    public ModelAndView roomEdit(@PathVariable (value = "id") int id) {
        Optional<List<RoomDTO>> rooms = roomRepository.findById(id);
        // If the optional is empty, redirect user to server error page.

        if (rooms.isEmpty()) {
            return new ModelAndView("redirect:/internal_server_error");
        }
        // Return a model and view, passing in the result of the operation to the view.
        return new ModelAndView("admin/editRoom").addObject("rooms",rooms.get());

    }

    /**
     * Created a route to take the edited form details
     * and update the room_name of the particular room_id.
     * Creating a new DTO by id which is already present in DB.
     * Update the name which is already fetched from DTO with the given one in form.
     * @param form will take the form data
     * @return will take you to manage_rooms page with a message.
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/room/edit/process_form")
    public ModelAndView editRoomProcessForm(RoomForm form) {
        // Create DTO by id given in the form to repository store.
        RoomDTO dto = new RoomDTO(form.getId());
        dto.setName(form.getName());
        //updating the data from repo with editRoom by passing the dto
        boolean result = roomRepository.editRoom(dto);

        // Return a model and view, passing in the result of the operation to the view.
        return new ModelAndView("admin/manage_rooms")
                .addObject("result", result);
    }

}

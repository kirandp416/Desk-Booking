package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.RoomCreateForm;
import uk.ac.cf.nsa.team2.deskbookingapp.form.RoomEditForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

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
    public ModelAndView addRoomProcessForm(RoomCreateForm form) {
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
     * This method was adapted from HOs method called bookingDelete in file BookingController.java,
     * that was used to delete booking from database.
     *
     *This method will take the id of the room which is passed through nav
     * and will pass it to repository to deleteRoom method
     * which will remove the id and it related items from database
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
     *This method will take id and name passed from URL parameters
     * and add them to DTO
     * Then pass them to room repository method which updates the values.
     * @param id is Room_id
     * @param name room_name
     * @return ModelandView of manage_rooms
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/admin/room/edit")
    public ModelAndView editRoom(@RequestParam(value = "id",defaultValue = "0")int id,@RequestParam(value = "name",defaultValue = "null")String name){
        ModelAndView mav = new ModelAndView();
            RoomDTO dto = new RoomDTO();
            dto.setId(id);
            dto.setName(name);
            if (roomRepository.editRoomAjax(dto)) {
                mav.setViewName("/admin/manage_rooms");
            } else {
                mav.setViewName("redirect:/internal_server_error");
            }
        return mav;
    }
    @GetMapping("/Home")
    public ModelAndView homePage() {
        return new ModelAndView("/Home");
    }

}

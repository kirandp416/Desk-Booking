package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.form.RoomForm;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.RoomRepository;

@Controller
public class RoomController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Route to get the admin page for adding a room.
     *
     * @return A model and view object.
     */
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

}

package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.service.DesktypeService;

import java.util.List;

@RestController
@RequestMapping("/admin/desk_type")
public class DesktypeController {
    @Autowired
    private DesktypeService desktypeService;

    /**
     * query desk type list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DeskTypeDTO> getUsers() {
        return desktypeService.findUserList();
    }

    /**
     * query a desk type by id
     *
     * @param deskTypeId
     * @return
     */
    @RequestMapping(value = "/{deskTypeId}", method = RequestMethod.GET)
    public DeskTypeDTO getUserById(@PathVariable("deskTypeId") int deskTypeId) {
        return desktypeService.findUserById(deskTypeId);
    }

    /**
     * delete a desk type
     *
     * @param deskTypeId
     * @return
     */
    @DeleteMapping(value = "/{deskTypeId}")
    public String delUserById(@PathVariable("deskTypeId") int deskTypeId) {
        int flag = desktypeService.delete(deskTypeId);
        if (flag == 1) {
            return "delete success!！";
        } else {
            return "delete  fail！！";
        }
    }

    /**
     * modify desk type information
     *

     */
    @PutMapping(value = "/edit")
    public String updateUser(@RequestBody DeskTypeDTO deskTypeDTO) {
        int t = desktypeService.update(deskTypeDTO);
        if (t == 1) {
            return "Modify Success！";
        } else {
            return "please input id!";
        }
    }

    /**
     * Add new desk type

     */
    @PostMapping(value = "/add")
    public String postUser(@RequestBody DeskTypeDTO deskTypeDTO) {
        int t = desktypeService.add(deskTypeDTO);
        if (t == 1) {
            return "Add success!";
        } else {
            return "Add fail!";
        }

    }


}

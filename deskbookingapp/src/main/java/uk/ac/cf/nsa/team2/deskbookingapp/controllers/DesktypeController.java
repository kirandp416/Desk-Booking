package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.service.DesktypeService;
import java.util.List;

/**
 * A controller for routes to manage desk type by an admin.
 */

@RestController
@RequestMapping("/admin/desk_type")
public class DesktypeController {
    @Autowired
    private DesktypeService desktypeService;

    /**
     * query desk type list
     * Generate a table of ids, Desk types and descriptions
     * and actions (update or delete) on this data.
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DeskTypeDTO> getDeskType() {
        return desktypeService.findDeskTypeList();
    }

    /**
     * query a desk type by id
     *The administrator queries a specific Desk type by id
     * @param deskTypeId
     * @return
     */
    @RequestMapping(value = "/{deskTypeId}", method = RequestMethod.GET)
    public DeskTypeDTO getUserById(@PathVariable("deskTypeId") int deskTypeId) {
        return desktypeService.findDeskTypeById(deskTypeId);
    }

    /**
     * delete a desk type
     *The admindeletes unnecessary data
     * @param deskTypeId
     * @return Return "delete success!！" on successful deletion
     *      and "delete  fail！！"on unsuccessful deletion
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
     * Modify desk type information
     *The admin can modify related data of Desk type,
     * connect to the database
     * and modify the database in real time
     * return
     * If the message "Modified successfully" is displayed
     * if the message "please input id!" is displayed.

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
     * Before adding a new Desk type
     * the administrator needs to fill in required information and
     * generate detailed data about the Desk type

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

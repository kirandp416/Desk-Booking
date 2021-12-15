package uk.ac.cf.nsa.team2.deskbookingapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomImgDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.IRoomImgRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.R;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/12
 */

/**
 * rest api
 */
@RestController
@RequestMapping("/Booking/Room")
public class RoomImgRestController {
    @Autowired
    private IRoomImgRepository roomImgRepository;

    /**
     * select data by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<RoomImgDTO> fetchImg(@PathVariable("id") int id) {
        return R.success(roomImgRepository.findImgById(id));
    }
}

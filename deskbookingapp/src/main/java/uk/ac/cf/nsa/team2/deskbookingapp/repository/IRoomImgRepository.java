package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomImgDTO;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/12
 */
public interface IRoomImgRepository {
    // method
    RoomImgDTO findImgById(int id);
}

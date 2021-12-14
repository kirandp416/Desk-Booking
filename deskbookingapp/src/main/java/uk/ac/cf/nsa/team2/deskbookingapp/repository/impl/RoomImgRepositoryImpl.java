package uk.ac.cf.nsa.team2.deskbookingapp.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.RoomImgDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.RoomImgMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.IRoomImgRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/12
 */
@Service
public class RoomImgRepositoryImpl implements IRoomImgRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RoomImgDTO findImgById(int room_id) {
        // avoid sql injection by StringBuffer
        List objects = new ArrayList();
        objects.add(room_id);
        StringBuffer sql = new StringBuffer("select * from room where room_id = ?");
        List<RoomImgDTO> list = jdbcTemplate.query(sql.toString(), objects.toArray(), new RoomImgMapper());
        if (list != null && list.size() > 0) {
            RoomImgDTO roomImgDTO = list.get(0);
            return roomImgDTO;
        } else {
            return null;
        }
    }
}

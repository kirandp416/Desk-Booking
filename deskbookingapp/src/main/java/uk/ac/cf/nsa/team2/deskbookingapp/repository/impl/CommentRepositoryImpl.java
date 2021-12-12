package uk.ac.cf.nsa.team2.deskbookingapp.repository.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingCommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.CommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.mapper.BookingCommentMapper;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.ICommentRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.DateUtil;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.ReflectUtil;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.Snowflake;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.StringUtil;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/1
 */
@Service
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private Snowflake snowflake;

    @Resource
    private DateUtil dateUtil;


    @Override
    public List<BookingCommentDTO> getOwnBooking(String username, int page, int num) {
        page = (page - 1) * num;
        if (page < 0) {
            page = 0;
        }
        // avoid sql injection by StringBuffer
        List objects = new ArrayList();
        // inner join
        StringBuffer sql = new StringBuffer("select m.room_id,m.desk_id,m.username,d.desk_name,m.room_name,m.booking_date" +
                " FROM desk d INNER JOIN (SELECT b.booking_id, b.username, b.booking_date,b.desk_id,b.room_id,r.room_name " +
                "FROM booking b INNER JOIN room r WHERE b.room_id = r.room_id) m WHERE d.desk_id = m.desk_id");
        if (username != null && username != "") {
            sql.append(" and username = ? ");
            objects.add(username);
        }
        sql.append(" and booking_date <= ? ");
        objects.add(dateUtil.Date(new Date()));
        sql.append("limit ?, ?");
        objects.add(page);
        objects.add(num);
        return jdbcTemplate.query(sql.toString(), objects.toArray(), new BookingCommentMapper());

    }

    // search sql
    @Override
    public List<CommentDTO> findAll() {
        String sql = "SELECT c.id,c.user_name,r.room_name,c.comment,c.img,c.created_time,c.updated_time FROM `comment` c" +
                " INNER JOIN room r WHERE r.room_id = c.room_id and deleted = \"N\" ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list.stream().map(l->
                ReflectUtil.mapToBean(l, CommentDTO.class)).collect(Collectors.toList());
    }

    // add sql
    @Override
    public String addComment(CommentDTO commentDTO) {
        String sql = "insert into comment(id,room_id,comment,user_name,img,created_time,updated_time) values(?,?,?,?,?,?,?)";

        // avoid sql injection by preparedStatement
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, snowflake.nextId());
            ps.setInt(2, commentDTO.getRoomId());
            ps.setString(3, commentDTO.getComment());
            ps.setString(4, commentDTO.getUserName());
            ps.setString(5, commentDTO.getImg());
            ps.setString(6, dateUtil.Time(new Date()));
            ps.setString(7, dateUtil.Time(new Date()));
            return ps;
        });
        return "add success";
    }

    @Override
    public String removeById(Long deletedId) {
        // soft deleted (Status delete)
        String sql = "update comment set deleted = \"Y\" where id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, deletedId);
            return ps;
        });
        return "delete success";
    }
}

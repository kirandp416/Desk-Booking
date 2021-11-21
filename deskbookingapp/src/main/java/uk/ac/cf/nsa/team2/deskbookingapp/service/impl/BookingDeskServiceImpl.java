package uk.ac.cf.nsa.team2.deskbookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.pojo.BookingDesk;
import uk.ac.cf.nsa.team2.deskbookingapp.service.BookigDeskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingDeskServiceImpl implements BookigDeskService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int add(BookingDesk bookingDesk) {

       String sql="INSERT INTO booking(bookingId,username,bookingDate,roomId,deskId) values ('?','?','?','?','?')";

        return jdbcTemplate.update(sql,
                bookingDesk.getBookingId(),
                bookingDesk.getUsername(),
                bookingDesk.getBookingDate(),
                bookingDesk.getRoomId(),
                bookingDesk.getDeskId());
    }

    @Override
    public int del(int bookingId) {
        String sql = "DELETE FROM booking WHERE booking_id = ?";
        return jdbcTemplate.update(sql, bookingId);
    }

    @Override
    public int edit(BookingDesk bookingDesk) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> fetch() {
        String sql="select * from booking";
        return jdbcTemplate.queryForList(sql);
    }
}

package uk.ac.cf.nsa.team2.deskbookingapp.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class test {
    private BookingService bookingService;

    public test(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<BookingDTO> getbookingInfo() {
        return bookingService.findbookingList();
    }
}

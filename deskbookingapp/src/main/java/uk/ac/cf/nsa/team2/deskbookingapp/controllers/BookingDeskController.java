package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.web.bind.annotation.*;
import uk.ac.cf.nsa.team2.deskbookingapp.pojo.BookingDesk;
import uk.ac.cf.nsa.team2.deskbookingapp.service.BookigDeskService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingDeskController {
    @Resource
    private BookigDeskService bookigDeskService;

    @GetMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id) {
        return bookigDeskService.del(id);
    }

    @GetMapping("/fetch")
    public List<Map<String,Object>> fetch() {
        return bookigDeskService.fetch();
    }

    @PostMapping("/add")
    public int add(@RequestBody BookingDesk bookingDesk){
        return bookigDeskService.add(bookingDesk);
    }
}

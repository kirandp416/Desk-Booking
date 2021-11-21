package uk.ac.cf.nsa.team2.deskbookingapp.service;

import uk.ac.cf.nsa.team2.deskbookingapp.pojo.BookingDesk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookigDeskService {
    int add(BookingDesk bookingDesk);
    int del(int id);
    int edit(BookingDesk bookingDesk);
    List<Map<String,Object>> fetch();





}

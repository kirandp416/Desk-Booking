package uk.ac.cf.nsa.team2.deskbookingapp.service;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> findbookingList();
}

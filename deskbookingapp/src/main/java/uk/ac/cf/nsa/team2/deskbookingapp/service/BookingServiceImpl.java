package uk.ac.cf.nsa.team2.deskbookingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingRepository;
import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookingDTO> findbookingList() {
        return bookingRepository.findbookingList();
    }
}

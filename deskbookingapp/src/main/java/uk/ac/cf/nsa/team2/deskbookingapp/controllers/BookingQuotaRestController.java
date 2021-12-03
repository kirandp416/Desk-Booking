package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingQuotaDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.BookingQuotaRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * REST API controller for booking quota.
 */
@Controller
public class BookingQuotaRestController {

    private final BookingQuotaRepository bookingQuotaRepository;

    /**
     * Initialises a new instance of the controller.
     *
     * @param bookingQuotaRepository a booking quota repository injected by Spring.
     */
    @Autowired
    public BookingQuotaRestController(BookingQuotaRepository bookingQuotaRepository) {
        this.bookingQuotaRepository = bookingQuotaRepository;
    }

    /**
     * Gets the booking quota for an employee for a room.
     *
     * @param roomId   the ID of the room.
     * @param username the employee's username.
     * @param response the HTTP response.
     * @return A JSON object.
     */
    @GetMapping("/api/booking_quota/rooms/{room_id}/users/{username}")
    public BookingQuotaDTO getBookingQuota(@PathVariable("room_id") int roomId, @PathVariable("username") String username,
                                           HttpServletResponse response) {
        // Query database for quota.
        Optional<Integer> quota = bookingQuotaRepository.findQuotaByRoom(roomId);
        Optional<Integer> usedQuota = bookingQuotaRepository.findUsedQuotaByEmployeeAndRoom(roomId, username);

        // If there was an error, return 500 error.
        if (quota.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        if (usedQuota.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        BookingQuotaDTO dto = new BookingQuotaDTO(quota.get(), usedQuota.get(), quota.get() - usedQuota.get());

        // If quota < used, then remaining is 0.
        // This can happen if quota went down because demand went up and employee
        // has managed to make more bookings than the quota before demand increased.
        if (quota.get() < usedQuota.get()) {
            dto.setRemaining(0);
        }

        return dto;
    }

}

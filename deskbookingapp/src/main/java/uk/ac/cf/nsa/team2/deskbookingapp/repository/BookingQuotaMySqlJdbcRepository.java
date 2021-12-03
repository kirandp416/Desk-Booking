package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.util.BookingQuotaCalculator;

import java.time.Month;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Optional;

@Repository
public class BookingQuotaMySqlJdbcRepository implements BookingQuotaRepository {

    private final JdbcTemplate jdbc;

    /**
     * Initialises a new instance of the repository.
     *
     * @param jdbc the JDBC template instance injected by Spring.
     */
    @Autowired
    public BookingQuotaMySqlJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Integer> findQuotaByRoom(int roomId) {
        // SQL to get count of desks for a room.
        final String sql1 = "SELECT COUNT(1) FROM desk WHERE room_id = ?;";

        // SQL to get number of employees who booked in the past 30 days.
        final String sql2 = "SELECT COUNT(DISTINCT username) FROM booking " +
                "WHERE book_timestamp BETWEEN CAST(? AS DATETIME) - INTERVAL 30 DAY " +
                "AND CAST(? AS DATETIME);";

        // Store current date/time.
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        try {
            // Execute queries.
            Integer desksCount = jdbc.queryForObject(sql1, Integer.class, roomId);
            Integer employeeCount = jdbc.queryForObject(sql2, Integer.class, now, now);

            // Ensure results are not null.
            // Return empty optional if so.
            if (desksCount == null || employeeCount == null) {
                return Optional.empty();
            }

            // Calculate and return booking quota.
            YearMonth yearMonth = YearMonth.of(now.getYear(), now.getMonth().getValue());

            return Optional.of(BookingQuotaCalculator.calculateQuota(desksCount, employeeCount,
                    yearMonth.lengthOfMonth(), yearMonth.lengthOfMonth() - now.getDayOfMonth()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        // Return empty optional as query failed.
        return Optional.empty();
    }

    @Override
    public Optional<Integer> findUsedQuotaByEmployee(String username) {
        final String sql = "SELECT COUNT(1) FROM booking " +
                "WHERE MONTH(book_timestamp) = MONTH(?) " +
                "AND username = ?;";

        try {
            return Optional.ofNullable(jdbc.queryForObject(sql, Integer.class,
                    OffsetDateTime.now(ZoneOffset.UTC).getMonth().getValue(), username));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        // Return empty optional as query failed.
        return Optional.empty();
    }

}

package uk.ac.cf.nsa.team2.deskbookingapp.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link BookingQuotaCalculator} class.
 */
@SpringBootTest
public class BookingQuotaCalculatorTests {

    /**
     * Tests the scenario where the raw quota value is greater than the number of days in the month.
     * The final quota value should be the number of days in the month.
     * See {@link BookingQuotaCalculator} for details of the algorithm.
     */
    @Test
    public void whenRawQuotaGreaterThanDaysInMonth_ThenQuotaEqualsDaysInMonth() {
        // Algorithm variables.
        int numberOfDesks = 25;
        int daysInMonth = 31;
        int daysRemaining = 6;
        int employeesPast30Days = 5;

        // Assert.
        assertEquals(daysInMonth, BookingQuotaCalculator.calculateQuota(numberOfDesks, employeesPast30Days,
                daysInMonth, daysRemaining));
    }

    /**
     * Tests the scenario where the raw quota value is less than the number of days in the month.
     * The final quota value should be the raw quota value.
     * See {@link BookingQuotaCalculator} for details of the algorithm.
     */
    @Test
    public void whenRawQuotaLessThanDaysInMonth_ThenQuotaEqualsRawQuota() {
        // Algorithm variables.
        int numberOfDesks = 20;
        int daysInMonth = 31;
        int daysRemaining = 29;
        int employeesPast30Days = 35;

        // Expected (manual calculation of the raw quota value using the algorithm).
        int expected = 13;

        // Assert.
        assertEquals(expected, BookingQuotaCalculator.calculateQuota(numberOfDesks, employeesPast30Days,
                daysInMonth, daysRemaining));
    }

}

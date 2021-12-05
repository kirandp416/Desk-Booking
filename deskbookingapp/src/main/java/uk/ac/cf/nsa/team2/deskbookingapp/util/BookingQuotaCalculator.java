package uk.ac.cf.nsa.team2.deskbookingapp.util;

/**
 * The booking quota calculator uses a custom designed algorithm to calculate a booking quota for a month
 * to ensure fairness in the booking system, giving as many employees a chance to book a desk.
 */
public class BookingQuotaCalculator {

    private static final int RESERVE = 10; // The reserve constant value.

    /**
     * Calculates the booking quota for a month.
     *
     * @param desks         the number of desks in a room available for booking in a day.
     * @param employees     the number of employees who booked in the past 30 days.
     * @param totalDays     the total number of days in the month.
     * @param remainingDays the remaining number of days in the month.
     * @return the calculated booking quota.
     */
    public static int calculateQuota(int desks, int employees, int totalDays, int remainingDays) {
        /*
         * Booking fairness algorithm.
         *
         * Q(raw) = (a * b) / (e + ((c / b) * d))
         * Q = Qr >= b ? b : Qr
         *
         * a = number of desks.
         * b = number of days in current month.
         * c = reserve constant value.
         * d = number of days remaining in current month.
         * e = number of employees who booked past 30 days.
         */
        float quotaRaw = (desks * totalDays) / (employees + (((float) RESERVE / totalDays) * remainingDays));

        return quotaRaw >= totalDays ? totalDays : (int) quotaRaw;
    }

}

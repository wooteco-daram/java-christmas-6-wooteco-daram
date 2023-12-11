package domain.benefit.discount;

import domain.event.EventConstants;
import java.time.LocalDate;
import java.time.YearMonth;

public class DiscountDateUtil {
    private DiscountDateUtil() {
        // No Instances
    }

    public static LocalDate getFirstWeekday() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> EventConstants.WEEKDAYS.contains(localDate.getDayOfWeek()))
                .findFirst()
                .orElseThrow();
    }

    public static LocalDate getFirstWeekend() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> EventConstants.WEEKENDS.contains(localDate.getDayOfWeek()))
                .findFirst()
                .orElseThrow();
    }
}

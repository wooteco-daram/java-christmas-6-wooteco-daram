package domain.event;

import domain.price.Price;
import java.time.DayOfWeek;
import java.util.List;

public class EventConstants {
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final List<DayOfWeek> weekdays = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
    );
    public static final List<DayOfWeek> weekends = List.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    );
    public static final List<Integer> starDays = List.of(3, 10, 17, 24, 25, 31);
    public static final Price MINIMUM_TOTAL_PRICE_FOR_DISCOUNT = new Price(10_000L);
}

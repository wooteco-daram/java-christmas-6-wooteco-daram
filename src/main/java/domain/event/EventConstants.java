package domain.event;

import java.time.DayOfWeek;
import java.util.List;

public class EventConstants {
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final List<DayOfWeek> WEEKDAYS = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
    );
    public static final List<DayOfWeek> WEEKENDS = List.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    );
    public static final int CHRISTMAS_EVENT_DAY = 25;

    private EventConstants() {
        // No instances
    }
}

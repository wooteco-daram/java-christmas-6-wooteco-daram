package domain.benefit.discount;

import domain.event.EventConstants;
import domain.menu.MenuBoard;
import domain.order.OrderGroup;
import domain.price.DiscountPrice;
import domain.reservation.ReservationDay;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayDiscount extends Discount {
    private static final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    private static final long DISCOUNT_PRICE_PER_DESSERT_COUNT = 2_023L;

    private final ReservationDay reservationDay;
    private final OrderGroup orderGroup;

    public WeekDayDiscount(
            final ReservationDay reservationDay,
            final OrderGroup orderGroup
    ) {
        this.reservationDay = reservationDay;
        this.orderGroup = orderGroup;
    }

    @Override
    public String getDiscountName() {
        return WEEKDAY_DISCOUNT_NAME;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        if (!isValidWeekDay()) {
            return DiscountPrice.empty();
        }

        final long countDessert = MenuBoard.DESSERT.countOverlap(orderGroup.getMenus());
        final long totalWeekDay = DISCOUNT_PRICE_PER_DESSERT_COUNT * countDessert;
        return DiscountPrice.from(totalWeekDay);
    }

    private boolean isValidWeekDay() {
        final LocalDate targetDate = LocalDate.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH,
                reservationDay.reservationDay()
        );
        final DayOfWeek dayOfWeek = targetDate.getDayOfWeek();
        return EventConstants.WEEKDAYS.contains(dayOfWeek);
    }
}

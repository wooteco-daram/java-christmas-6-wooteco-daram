package domain.benefit.discount;

import domain.event.EventConstants;
import domain.price.DiscountPrice;
import domain.reservation.ReservationDay;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount extends Discount {
    private static final String SPECIAL_DISCOUNT_NAME = "증정 이벤트";
    private static final long SPECIAL_DISCOUNT_PRICE = 1_000L;
    private final ReservationDay reservationDay;

    public SpecialDiscount(final ReservationDay reservationDay) {
        this.reservationDay = reservationDay;
    }

    @Override
    public String getDiscountName() {
        return SPECIAL_DISCOUNT_NAME;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        final int day = reservationDay.reservationDay();

        if (!isValidSunday(day) && !isValidChristmasDay(day)) {
            return DiscountPrice.empty();
        }

        return DiscountPrice.from(SPECIAL_DISCOUNT_PRICE);
    }

    private boolean isValidSunday(final int day) {
        final LocalDate targetDate = LocalDate.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH,
                day
        );
        final DayOfWeek dayOfWeek = targetDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isValidChristmasDay(final int day) {
        return day == EventConstants.CHRISTMAS_EVENT_DAY;
    }
}

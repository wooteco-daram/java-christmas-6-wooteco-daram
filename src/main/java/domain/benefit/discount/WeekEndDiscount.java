package domain.benefit.discount;

import domain.event.EventConstants;
import domain.menu.MenuBoard;
import domain.order.OrderGroup;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekEndDiscount extends Discount {
    private static final String WEEKEND_DISCOUNT_NAME = "주말 할인";
    private static final long DISCOUNT_PRICE_PER_MAIN_COUNT = 2_023L;
    private final ReservationDay reservationDay;
    private final OrderGroup orderGroup;

    public WeekEndDiscount(
            final ReservationDay reservationDay,
            final OrderGroup orderGroup
    ) {
        this.reservationDay = reservationDay;
        this.orderGroup = orderGroup;
    }

    @Override
    public String getDiscountName() {
        return WEEKEND_DISCOUNT_NAME;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        final LocalDate targetDate = LocalDate.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH,
                reservationDay.reservationDay()
        );
        final DayOfWeek dayOfWeek = targetDate.getDayOfWeek();

        if (!EventConstants.weekends.contains(dayOfWeek)) {
            return DiscountPrice.empty();
        }

        final long countMain = MenuBoard.MAIN.countOverlap(orderGroup.getMenus());
        final long totalWeekDay = DISCOUNT_PRICE_PER_MAIN_COUNT * countMain;
        return new DiscountPrice(new Price(totalWeekDay));
    }
}

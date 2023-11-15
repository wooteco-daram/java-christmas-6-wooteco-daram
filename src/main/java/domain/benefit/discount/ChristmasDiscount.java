package domain.benefit.discount;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;

public class ChristmasDiscount extends Discount {
    private static final String CHRISTMAS_DISCOUNT_NAME = "크리스마스 디데이 할인";
    private static final int CHRISTMAS_EVENT_END_INCLUSIVE_DAY = 25;
    private static final long START_DISCOUNT = 1_000L;
    private static final long INCREMENT_RATE = 100L;
    private final ReservationDay reservationDay;

    public ChristmasDiscount(final ReservationDay reservationDay) {
        this.reservationDay = reservationDay;
    }

    @Override
    public String getDiscountName() {
        return CHRISTMAS_DISCOUNT_NAME;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        if (reservationDay.afterFrom(CHRISTMAS_EVENT_END_INCLUSIVE_DAY)) {
            return DiscountPrice.empty();
        }

        final long christmasDiscount = START_DISCOUNT + (reservationDay.reservationDay() - 1) * INCREMENT_RATE;
        return new DiscountPrice(new Price(christmasDiscount));
    }
}

package domain.benefit.discount;

import domain.event.EventConstants;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;

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
        if (!EventConstants.starDays.contains(day)) {
            return DiscountPrice.empty();
        }

        return new DiscountPrice(new Price(SPECIAL_DISCOUNT_PRICE));
    }
}

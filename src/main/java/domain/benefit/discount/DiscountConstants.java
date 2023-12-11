package domain.benefit.discount;

import domain.price.Price;

public class DiscountConstants {
    private DiscountConstants() {
        // No Instances
    }

    public static final Price MINIMUM_TOTAL_PRICE_FOR_DISCOUNT = Price.from(10_000L);
}

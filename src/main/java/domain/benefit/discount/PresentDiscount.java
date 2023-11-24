package domain.benefit.discount;

import domain.price.DiscountPrice;

public class PresentDiscount extends Discount {
    private static final String CHRISTMAS_DISCOUNT_NAME = "증정 이벤트";

    private final DiscountPrice totalDiscountPrice;

    public PresentDiscount(final DiscountPrice totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        return totalDiscountPrice;
    }

    @Override
    public String getDiscountName() {
        return CHRISTMAS_DISCOUNT_NAME;
    }
}

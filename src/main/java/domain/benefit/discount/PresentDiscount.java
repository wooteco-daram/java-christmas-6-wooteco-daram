package domain.benefit.discount;

import domain.order.PresentOrderGroup;
import domain.price.DiscountPrice;
import domain.price.TotalDiscountPrice;

public class PresentDiscount extends Discount {
    private static final String CHRISTMAS_DISCOUNT_NAME = "증정 이벤트";

    private final PresentOrderGroup presentOrderGroup;

    public PresentDiscount(final PresentOrderGroup presentOrderGroup) {
        this.presentOrderGroup = presentOrderGroup;
    }

    @Override
    public DiscountPrice getDiscountPrice() {
        final TotalDiscountPrice totalDiscountPrice = presentOrderGroup.getTotalDiscountPrice();
        return totalDiscountPrice.getDiscountPrice();
    }

    @Override
    public String getDiscountName() {
        return CHRISTMAS_DISCOUNT_NAME;
    }
}

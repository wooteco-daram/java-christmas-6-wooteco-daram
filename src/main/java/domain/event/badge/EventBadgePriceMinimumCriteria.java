package domain.event.badge;

import domain.price.DiscountPrice;

public enum EventBadgePriceMinimumCriteria {
    NONE(DiscountPrice.empty()),
    STAR(DiscountPrice.from(5_000)),
    TREE(DiscountPrice.from(10_000)),
    SANTA(DiscountPrice.from(20_000));

    private final DiscountPrice discountPrice;

    EventBadgePriceMinimumCriteria(final DiscountPrice discountPrice) {
        this.discountPrice = discountPrice;
    }

    public boolean isSatisfied(final DiscountPrice otherDiscountPrice) {
        return discountPrice.isLessThanEqualTo(otherDiscountPrice);
    }
}

package domain.benefit.discount;

import domain.price.DiscountPrice;

public abstract class Discount {
    public boolean exist() {
        final DiscountPrice discountPrice = getDiscountPrice();
        return discountPrice.isGreaterThan(DiscountPrice.empty());
    }

    public abstract String getDiscountName();

    public abstract DiscountPrice getDiscountPrice();

    @Override
    public String toString() {
        return getDiscountName() + ": " + getDiscountPrice();
    }
}

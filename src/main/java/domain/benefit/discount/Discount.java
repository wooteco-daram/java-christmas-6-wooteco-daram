package domain.benefit.discount;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalPrice;

public abstract class Discount {
    public boolean isValidDiscount(final TotalPrice totalPrice) {
        if (!shouldOverMinimumTotalPrice(totalPrice)) {
            return false;
        }

        final Price discountPrice = getDiscountPrice().price();
        return discountPrice.price() > 0;
    }

    private boolean shouldOverMinimumTotalPrice(final TotalPrice totalPrice) {
        final Price price = totalPrice.getPrice();
        return price.isGreaterThanEqualTo(DiscountConstants.MINIMUM_TOTAL_PRICE_FOR_DISCOUNT);
    }

    public abstract String getDiscountName();

    public abstract DiscountPrice getDiscountPrice();

    @Override
    public String toString() {
        return getDiscountName() + ": " + getDiscountPrice();
    }
}

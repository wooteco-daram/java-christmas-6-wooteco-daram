package domain.price;

import java.util.List;
import java.util.Objects;

public class TotalDiscountPrice {
    private static final String TOTAL_DISCOUNT_PRICE_EMPTY_STRING = "없음";
    private final DiscountPrice discountPrice;

    public TotalDiscountPrice(final List<DiscountPrice> discountPrices) {
        discountPrice = discountPrices.stream()
                .reduce(DiscountPrice::add)
                .orElse(DiscountPrice.empty());
    }

    public DiscountPrice getDiscountPrice() {
        return discountPrice;
    }

    public Price getPrice() {
        return discountPrice.price();
    }

    @Override
    public String toString() {
        if (Objects.equals(discountPrice, DiscountPrice.empty())) {
            return TOTAL_DISCOUNT_PRICE_EMPTY_STRING;
        }

        return discountPrice.toString();
    }
}

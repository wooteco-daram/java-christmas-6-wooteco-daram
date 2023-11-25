package domain.price;

import java.util.List;

public class TotalDiscountPrice {
    private final Price totalDiscountPrice;

    public TotalDiscountPrice(final List<DiscountPrice> discountPrices) {
        totalDiscountPrice = discountPrices.stream()
                .map(DiscountPrice::price)
                .reduce(Price::add)
                .orElse(Price.empty());
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    @Override
    public String toString() {
        if (totalDiscountPrice.price() == 0) {
            return "0원";
        }

        return "-" + totalDiscountPrice.toString();
    }
}

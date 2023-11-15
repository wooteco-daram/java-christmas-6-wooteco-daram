package domain.price;

import java.util.List;

public class TotalDiscountPrice {
    private final Price totalDiscountPrice;

    public TotalDiscountPrice(final List<DiscountPrice> discountPrices) {
        totalDiscountPrice = discountPrices.stream()
                .map(DiscountPrice::price)
                .reduce(Price::add)
                .orElse(new Price(0));
    }

    public static TotalDiscountPrice empty() {
        return new TotalDiscountPrice(List.of());
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    @Override
    public String toString() {
        if (totalDiscountPrice.price() == 0) {
            return "없음";
        }

        return "-" + totalDiscountPrice.toString();
    }
}

package domain.price;

import java.util.List;
import java.util.Objects;

public class TotalDiscountPrice {
    private final Price price;

    public TotalDiscountPrice(final List<DiscountPrice> discountPrices) {
        price = discountPrices.stream()
                .map(DiscountPrice::price)
                .reduce(Price::add)
                .orElse(Price.empty());
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (Objects.equals(price, Price.empty())) {
            return price.toString();
        }

        return "-" + price;
    }
}

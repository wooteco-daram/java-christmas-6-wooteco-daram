package domain.price;

import java.util.List;
import java.util.Objects;

public class TotalPrice {
    private static final String TOTAL_PRICE_EMPTY_STRING = "없음";

    private final Price price;

    public TotalPrice(final List<Price> prices) {
        price = prices.stream()
                .reduce(Price::add)
                .orElse(Price.empty());
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (Objects.equals(price, Price.empty())) {
            return TOTAL_PRICE_EMPTY_STRING;
        }

        return price.toString();
    }
}

package domain.price;

import java.util.List;

public class TotalPrice {

    private final Price price;

    public TotalPrice(final List<Price> prices) {
        price = prices.stream()
                .reduce(Price::add)
                .orElse(Price.empty());
    }

    public Price getPrice() {
        return new Price(price.price());
    }

    @Override
    public String toString() {
        return price.toString();
    }
}

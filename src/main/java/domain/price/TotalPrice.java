package domain.price;

import java.util.List;

public class TotalPrice {

    private final Price totalPrice;

    public TotalPrice(final List<Price> prices) {
        totalPrice = prices.stream()
                .reduce(Price::add)
                .orElse(Price.empty());
    }

    public Price getTotalPrice() {
        return new Price(totalPrice.price());
    }

    @Override
    public String toString() {
        return totalPrice.toString();
    }
}

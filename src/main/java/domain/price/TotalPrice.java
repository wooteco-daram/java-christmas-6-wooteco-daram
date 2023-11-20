package domain.price;

import java.util.List;

public class TotalPrice {

    private final Price totalPrice;

    public TotalPrice(final List<Price> prices) {
        totalPrice = prices.stream()
                .reduce(Price::add)
                .orElse(new Price(0));
    }

    public Price getTotalPrice() {
        return new Price(totalPrice.price());
    }

    @Override
    public String toString() {
        return totalPrice.toString();
    }
}

package event.badge;

import menu.Price;

public enum EventBadgePriceMinimumCriteria {
    NONE(new Price(0)),
    STAR(new Price(5_000)),
    TREE(new Price(10_000)),
    SANTA(new Price(20_000));

    private final Price price;

    EventBadgePriceMinimumCriteria(final Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public boolean isSatisfied(final Price otherPrice) {
        return otherPrice.price() >= price.price();
    }
}

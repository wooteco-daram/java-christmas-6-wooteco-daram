package event.badge;

import java.util.Arrays;
import java.util.function.Predicate;
import menu.Price;

public enum EventBadgeType {
    NONE("없음", price -> price.price() < 5_000),
    STAR("별", price -> price.price() >= 5_000),
    TREE("트리", price -> price.price() >= 10_000),
    SANTA("산타", price -> price.price() >= 20_000);

    private final String name;
    private final Predicate<Price> pricePredicate;

    EventBadgeType(
            final String name,
            final Predicate<Price> pricePredicate
    ) {
        this.name = name;
        this.pricePredicate = pricePredicate;
    }

    public String getName() {
        return name;
    }

    public boolean test(final Price price) {
        return pricePredicate.test(price);
    }

    public static EventBadgeType findByPrice(final Price price) {
        return Arrays.stream(values())
                .filter(eventBadgeType -> eventBadgeType.test(price))
                .reduce((first, second) -> second)
                .orElse(EventBadgeType.NONE);
    }
}

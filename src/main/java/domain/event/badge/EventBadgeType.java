package domain.event.badge;

import java.util.Arrays;
import java.util.function.Predicate;
import domain.price.Price;

public enum EventBadgeType {
    NONE("없음", EventBadgePriceMinimumCriteria.NONE::isSatisfied),
    STAR("별", EventBadgePriceMinimumCriteria.STAR::isSatisfied),
    TREE("트리", EventBadgePriceMinimumCriteria.TREE::isSatisfied),
    SANTA("산타", EventBadgePriceMinimumCriteria.SANTA::isSatisfied);

    private final String name;
    private final Predicate<Price> pricePredicate;

    EventBadgeType(
            final String name,
            final Predicate<Price> pricePredicate
    ) {
        this.name = name;
        this.pricePredicate = pricePredicate;
    }

    public boolean test(final Price price) {
        return pricePredicate.test(price);
    }

    public static EventBadgeType findByPrice(final Price price) {
        return Arrays.stream(values())
                .filter(eventBadgeType -> eventBadgeType.test(price))
                .reduce((unused, eventBadgeType) -> eventBadgeType)
                .orElse(EventBadgeType.NONE);
    }

    @Override
    public String toString() {
        return name;
    }
}

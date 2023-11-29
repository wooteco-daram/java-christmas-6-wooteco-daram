package domain.event.badge;

import domain.price.DiscountPrice;
import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadgeType {
    NONE("없음", EventBadgePriceMinimumCriteria.NONE::isSatisfied),
    STAR("별", EventBadgePriceMinimumCriteria.STAR::isSatisfied),
    TREE("트리", EventBadgePriceMinimumCriteria.TREE::isSatisfied),
    SANTA("산타", EventBadgePriceMinimumCriteria.SANTA::isSatisfied);

    private final String name;
    private final Predicate<DiscountPrice> pricePredicate;

    EventBadgeType(
            final String name,
            final Predicate<DiscountPrice> pricePredicate
    ) {
        this.name = name;
        this.pricePredicate = pricePredicate;
    }

    public boolean test(final DiscountPrice discountPrice) {
        return pricePredicate.test(discountPrice);
    }

    public static EventBadgeType findByDiscountPrice(final DiscountPrice discountPrice) {
        return Arrays.stream(values())
                .filter(eventBadgeType -> eventBadgeType.test(discountPrice))
                .reduce((unused, eventBadgeType) -> eventBadgeType)
                .orElse(EventBadgeType.NONE);
    }

    @Override
    public String toString() {
        return name;
    }
}

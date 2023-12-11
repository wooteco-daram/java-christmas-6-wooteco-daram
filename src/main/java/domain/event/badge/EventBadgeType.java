package domain.event.badge;

import domain.price.DiscountPrice;
import domain.price.Price;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public enum EventBadgeType {
    NONE("없음", EventBadgePriceMinimumCriteria.NONE),
    STAR("별", EventBadgePriceMinimumCriteria.STAR),
    TREE("트리", EventBadgePriceMinimumCriteria.TREE),
    SANTA("산타", EventBadgePriceMinimumCriteria.SANTA);

    private final String name;
    private final EventBadgePriceMinimumCriteria criteria;

    EventBadgeType(
            final String name,
            final EventBadgePriceMinimumCriteria criteria
    ) {
        this.name = name;
        this.criteria = criteria;
    }

    public boolean isSatisfied(final DiscountPrice discountPrice) {
        return criteria.isSatisfied(discountPrice);
    }

    public static EventBadgeType findByDiscountPrice(final DiscountPrice discountPrice) {
        return Arrays.stream(values())
                .filter(eventBadgeType -> eventBadgeType.isSatisfied(discountPrice))
                .max(Comparator.comparingLong(eventBadgeType -> {
                    final DiscountPrice criteriaDiscountPrice = eventBadgeType.criteria.getDiscountPrice();
                    final Price price = criteriaDiscountPrice.price();
                    return price.price();
                }))
                .orElse(EventBadgeType.NONE);
    }

    @Override
    public String toString() {
        return name;
    }
}

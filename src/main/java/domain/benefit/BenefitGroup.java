package domain.benefit;

import domain.benefit.discount.Discount;
import domain.benefit.discount.DiscountConstants;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import java.util.List;
import java.util.stream.Collectors;

public class BenefitGroup {
    private final List<Discount> discounts;

    public BenefitGroup(final List<Discount> discounts, final TotalPrice totalPrice) {
        this.discounts = getValidDiscounts(discounts, totalPrice);
    }

    public TotalDiscountPrice getTotalDiscountPrice() {
        final List<DiscountPrice> discountPrices = discounts.stream()
                .map(Discount::getDiscountPrice)
                .toList();
        return new TotalDiscountPrice(discountPrices);
    }

    public List<Discount> getValidDiscounts(final List<Discount> discounts, final TotalPrice totalPrice) {
        if (!shouldOverMinimumTotalPrice(totalPrice)) {
            return List.of();
        }

        return discounts.stream()
                .filter(Discount::exist)
                .toList();
    }

    private boolean shouldOverMinimumTotalPrice(final TotalPrice totalPrice) {
        final Price price = totalPrice.getPrice();
        return price.isGreaterThanEqualTo(DiscountConstants.MINIMUM_TOTAL_PRICE_FOR_DISCOUNT);
    }

    @Override
    public String toString() {
        if (discounts.isEmpty()) {
            return "없음";
        }

        return discounts.stream()
                .map(Discount::toString)
                .collect(Collectors.joining("\n"));
    }
}

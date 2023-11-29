package domain.benefit;

import domain.benefit.discount.Discount;
import domain.price.DiscountPrice;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import java.util.List;
import java.util.stream.Collectors;

public class BenefitGroup {
    private final List<Discount> discounts;

    public BenefitGroup(final List<Discount> discounts, final TotalPrice totalPrice) {
        this.discounts = discounts.stream()
                .filter(discount -> discount.isValidDiscount(totalPrice))
                .toList();
    }

    public TotalDiscountPrice getTotalDiscountPrice() {
        final List<DiscountPrice> discountPrices = discounts.stream()
                .map(Discount::getDiscountPrice)
                .toList();
        return new TotalDiscountPrice(discountPrices);
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

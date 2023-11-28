package domain.order;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import java.util.List;
import java.util.stream.Collectors;

public class PresentOrderGroup {
    private final List<PresentOrder> presentMenus;

    public PresentOrderGroup(final TotalPrice totalPrice) {
        presentMenus = PresentOrder.getAvailablePresentMenus(totalPrice.getPrice());
    }

    public TotalDiscountPrice getTotalDiscountPrice() {
        final List<DiscountPrice> discountPrices = presentMenus.stream()
                .map(PresentOrder::getDiscountPrice)
                .toList();

        return new TotalDiscountPrice(discountPrices);
    }

    @Override
    public String toString() {
        if (presentMenus.isEmpty()) {
            return "없음";
        }

        return presentMenus.stream()
                .map(PresentOrder::toString)
                .collect(Collectors.joining("\n"));
    }
}

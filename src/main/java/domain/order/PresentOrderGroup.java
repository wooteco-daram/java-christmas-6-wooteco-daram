package domain.order;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalPrice;
import java.util.List;
import java.util.stream.Collectors;

public class PresentOrderGroup {
    private final List<PresentOrder> presentMenus;

    public PresentOrderGroup(final TotalPrice totalPrice) {
        presentMenus = PresentOrder.getAvailablePresentMenus(totalPrice.getPrice());
    }

    public DiscountPrice getTotalDiscountPrice() {
        return presentMenus.stream()
                .map(PresentOrder::getDiscountPrice)
                .reduce(DiscountPrice::add)
                .orElse(DiscountPrice.empty());
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

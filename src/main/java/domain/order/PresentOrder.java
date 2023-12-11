package domain.order;

import domain.menu.Menu;
import domain.price.DiscountPrice;
import domain.price.Price;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum PresentOrder {
    CHAMPAGNE(
            Menu.CHAMPAGNE,
            OrderCount.from(1L),
            price -> price.price() >= 120_000L
    );

    private final Menu menu;
    private final OrderCount orderCount;
    private final Predicate<Price> presentCondition;

    PresentOrder(
            final Menu menu,
            final OrderCount orderCount,
            final Predicate<Price> presentCondition
    ) {
        this.menu = menu;
        this.orderCount = orderCount;
        this.presentCondition = presentCondition;
    }

    public DiscountPrice getDiscountPrice() {
        Price price = menu.getPrice();
        Price multiplyPrice = price.multiply(orderCount.count());
        return new DiscountPrice(multiplyPrice);
    }

    public static List<PresentOrder> getAvailablePresentMenus(final Price price) {
        return Arrays.stream(values())
                .filter(presentMenu -> presentMenu.presentCondition.test(price))
                .toList();
    }

    @Override
    public String toString() {
        return menu.getName() + " " + orderCount.count() + "개";
    }
}

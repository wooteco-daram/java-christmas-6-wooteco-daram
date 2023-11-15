package domain.order;

import domain.menu.Menu;
import domain.price.Price;
import exception.ErrorMessage;
import exception.GlobalException;

public class Order {
   private static final String ORDER_SPLIT_DELIMITER = "-";

    private final Menu menu;
    private final OrderCount count;

    public Order(
            final Menu menu,
            final OrderCount count
    ) {
        this.menu = menu;
        this.count = count;
    }

    public static Order from(final String order) {
        final String[] orderSplit = order.split(ORDER_SPLIT_DELIMITER);
        validateOrderSplit(orderSplit);

        final Menu menu = Menu.findByName(orderSplit[0]);
        final OrderCount orderCount = OrderCount.from(orderSplit[1]);

        return new Order(menu, orderCount);
    }

    private static void validateOrderSplit(final String[] orderSplit) {
        if (orderSplit.length != 2) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Price getPrice() {
        return menu.getPrice().multiply(count.count());
    }

    public OrderCount getCount() {
        return count;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + count.count() + "개";
    }
}
